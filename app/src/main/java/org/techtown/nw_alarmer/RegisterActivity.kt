package org.techtown.nw_alarmer

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.techtown.nw_alarmer.databinding.ActivityRegisterBinding
import org.techtown.nw_alarmer.localDB.MyWtList
import org.techtown.nw_alarmer.localDB.MyWtListRecycler
import org.techtown.nw_alarmer.localDB.WTViewModel
import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_HIGH
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_ONE_SHOT
import android.app.PendingIntent.getActivity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Handler
import android.os.Looper
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import org.techtown.nw_alarmer.Constants.CHANNEL_ID
import org.techtown.nw_alarmer.Constants.CHANNEL_NAME
import org.techtown.nw_alarmer.parsingClass.Parsing
import kotlin.random.Random


class RegisterActivity : AppCompatActivity() {

    private lateinit var mBinding : ActivityRegisterBinding

    lateinit var model : WTViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding  = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(mBinding .root)

        model = ViewModelProvider(this).get(WTViewModel::class.java)

        val title = intent.getStringExtra("title")
        val img = intent.getStringExtra("img")
        mBinding.webTitle.text = title

        Glide.with(this)
            .load(img)
            .into(mBinding .webImg)


        mBinding.registerButton.setOnClickListener {

                var exist : Boolean = false

                //??????????????? ?????? ???????????? roomDB??? ????????? ??????
                model.getAll().observe(this@RegisterActivity) { list ->

                    for(i: Int in 0..list.size-1){
                        if(list.get(i).wtTitle.equals(title)) {
                            //Log.e("TAG", "?????? ?????? ???????????????.!!")
                            exist = true
                            break
                        }
                    }

                }

            Handler().postDelayed({

                if(!exist){
                    //???????????? model??? ?????????????????? ?????? ??? ?????? ????????????
                    Toast.makeText(this,"?????????????????????.",Toast.LENGTH_SHORT).show()

                    lifecycleScope.launch(Dispatchers.IO){
                        model.insert(MyWtList(0,title,img,true))
                    }//???????????? ??????
                }
                else{
                    Toast.makeText(this,"?????? ????????? ?????? ?????????.",Toast.LENGTH_SHORT).show()
                }

                finish()

            }, 2000)
            //2?????? ??????

        }


    }


    /*
    private fun callAlarm(title : String) {

        val intent = Intent(this, MainActivity::class.java)

        val notificationManager = this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notificationID = Random.nextInt()
        //???????????? ID ??????

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel(notificationManager)
        }

        val pendingIntent =
            PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        val notification = NotificationCompat.Builder(this, Constants.CHANNEL_ID)
            .setContentTitle("?????? ??????")
            .setContentText(title + "????????????")
            .setAutoCancel(true)
            .setSmallIcon(R.drawable.alarm)
            .setContentIntent(pendingIntent)
            .build()

        notificationManager.notify(notificationID, notification)

    }//?????? ??????

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(notificationManager: NotificationManager) {
        val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, IMPORTANCE_HIGH).apply {
            description = "Channel Description"
            enableLights(true)
            lightColor = Color.GREEN
        }
        notificationManager.createNotificationChannel(channel)
    }
    */

}





