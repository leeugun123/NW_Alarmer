package org.techtown.nw_alarmer.BackgroundAlarmWorker

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.lifecycle.LiveData
import androidx.work.*
import org.techtown.nw_alarmer.Constants
import org.techtown.nw_alarmer.MainActivity
import org.techtown.nw_alarmer.localDB.ListDatabase
import org.techtown.nw_alarmer.localDB.MyWtList
import org.techtown.nw_alarmer.localDB.WtListDao
import kotlin.random.Random
import org.techtown.nw_alarmer.R

class AlarmWorker(appContext: Context,params: WorkerParameters) : Worker(appContext,params){

    private val wtlistDao : WtListDao

    init {

        var db = ListDatabase.getInstance(appContext)
        wtlistDao = db!!.wtListDao()

    }//db 초기화 및 접근




    override fun doWork(): Result{

        Log.e("TAG", "백그라운드에서 작업을 수행 중입니다.!!!!")

        return try {

            var mylist : LiveData<List<MyWtList>> = wtlistDao.getList()

            //html 파싱하여 구현하기
            //Work


            //알람 구현
            val intent = Intent(applicationContext, MainActivity::class.java)

            val notificationManager = applicationContext.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            val notificationID = Random.nextInt()
            //랜덤으로 ID 생성

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                createNotificationChannel(notificationManager)
            }

            val pendingIntent =
                PendingIntent.getActivity(applicationContext, 0, intent, PendingIntent.FLAG_IMMUTABLE)

            val notification = NotificationCompat.Builder(applicationContext, Constants.CHANNEL_ID)
                .setContentTitle("알람 시작")
                .setContentText("웹툰이 업데이트 되었습니다..")
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.alarm)
                .setContentIntent(pendingIntent)
                .build()

            notificationManager.notify(notificationID, notification)

            Log.e(TAG, "백그라운드 작업 성공")

            return Result.success()
            //성공 반환

        }catch (e: Exception){

            Log.e(TAG, "백그라운드 작업 실패")
            Result.failure()

        }


    }//백그라운드에서 동작

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(notificationManager: NotificationManager) {
        val channel = NotificationChannel(Constants.CHANNEL_ID, Constants.CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH).apply {
            description = "Channel Description"
            enableLights(true)
            lightColor = Color.GREEN
        }
        notificationManager.createNotificationChannel(channel)
    }

}