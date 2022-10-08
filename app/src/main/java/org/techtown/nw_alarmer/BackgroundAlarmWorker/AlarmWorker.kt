package org.techtown.nw_alarmer.BackgroundAlarmWorker

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
import androidx.work.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import org.techtown.nw_alarmer.Constants
import org.techtown.nw_alarmer.JsoupCrawlerExample
import org.techtown.nw_alarmer.MainActivity
import kotlin.random.Random
import org.techtown.nw_alarmer.R
import org.techtown.nw_alarmer.localDB.WTRepository

class AlarmWorker(appContext: Context,params: WorkerParameters) : Worker(appContext,params){

    //private val repository = WTRepository(appContext as Application)
    //repo에서 접근

    private val webToonUrl = "https://comic.naver.com/webtoon/weekday"
    //웹툰 url

    override fun doWork(): Result{

        Log.e("TAG", "백그라운드에서 작업을 수행 중입니다.!!!!")

        return try {

            //val wtLists = repository.getAll()
            //viewModel에서 접근하는 방법은? 또 콜백함수로 구현해야 하는가??

            parsing()
            //파싱하기

            Log.e(TAG, "백그라운드 작업 성공")

            return Result.success()
            //성공 반환

        }catch (e: Exception){

            Log.e(TAG, "백그라운드 작업 실패")
            Result.failure()

        }


    }//백그라운드에서 동작

    private fun parsing() {
        //레포에서 현재 데이터 가져오기

        val scope = GlobalScope

        scope.launch {

            //SSL 체크
            if(webToonUrl.indexOf("https://") >= 0){
                JsoupCrawlerExample.setSSL();
            }//https:로 시작하는경우 setSSL() 실행하여 우회

            val doc = Jsoup.connect(webToonUrl).get()
            //HTML 가져오기

            for(i in 0..6){

                val dayList = doc.select("div.col_inner")[i].select("li")

                for(j in dayList){

                    var wtIntel = j.select("img")//한 웹툰의 정보들

                    var title = ""
                    var img = ""
                    var up = ""

                    var upIntel = j.select("em")
                    //업데이트 정보

                    for(k in wtIntel){

                        title = k.absUrl("title").replace("https://comic.naver.com/webtoon/","")
                        //Log.e("TAG",title)

                    }//웹툰 제목 가져오기

                    for(k in wtIntel){

                        img = k.absUrl("src")
                        //Log.e("TAG",img)

                    }//웹툰 img 가져오기

                    for(k in upIntel){
                        up = k.absUrl("class").replace("https://comic.naver.com/webtoon/", "")
                    }//웹툰 up 정보 가져오기

                    if(up.equals("ico_updt"))
                        up = "Up"
                    else if(up.equals("ico_break"))
                        up = "휴재"

                    Log.e("TAG",title+" "+img+" "+up)


                }

            }//월요일부터 일요일까지 가져오기


        }

    }

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