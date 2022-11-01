package org.techtown.nw_alarmer.BackgroundAlarmWorker


import android.annotation.SuppressLint
import android.app.Application
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
import androidx.core.content.getSystemService
import androidx.lifecycle.*
import androidx.work.*
import org.techtown.nw_alarmer.Constants
import org.techtown.nw_alarmer.MainActivity
import kotlin.random.Random
import org.techtown.nw_alarmer.R
import org.techtown.nw_alarmer.localDB.*

class AlarmWorker(appContext: Context,params: WorkerParameters) : Worker(appContext,params){

    private lateinit var model : WTViewModel

    @SuppressLint("WrongThread")
    override fun doWork(): Result{

        Log.e("TAG", "백그라운드에서 작업을 수행 중입니다.!!!!")

        return try {

            //callAlarm()
            return Result.success()
            //성공 반환
        }catch (e: Exception){
            Log.e(TAG, "백그라운드 작업 실패")
            Result.failure()
        }


    }//백그라운드에서 동작



    private fun callAlarm() {

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

    }//알람 구현

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

