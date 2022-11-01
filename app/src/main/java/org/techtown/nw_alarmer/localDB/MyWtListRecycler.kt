package org.techtown.nw_alarmer.localDB

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.bumptech.glide.Glide
import org.techtown.nw_alarmer.BackgroundAlarmWorker.AlarmWorker
import org.techtown.nw_alarmer.Constants
import org.techtown.nw_alarmer.MainActivity
import org.techtown.nw_alarmer.R
import org.techtown.nw_alarmer.databinding.MywtViewBinding
import java.util.*
import kotlin.random.Random


class MyWtListRecycler (listener : OnItemClick): RecyclerView.Adapter<MyWtListRecycler.ViewHolder>(){

    private val items = ArrayList<MyWtList>()
    private val mCallback = listener

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MyWtListRecycler.ViewHolder, position: Int) {

        holder.bind(items[position])

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = MywtViewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)

    }

    fun setList(list : List<MyWtList>) {
        items.clear()
        items.addAll(list)
    }


    //  각 항목에 필요한 기능을 구현
    inner class ViewHolder(private val binding : MywtViewBinding) : RecyclerView.ViewHolder(binding.root) {

        val handler = Handler()

        val runnable = object : Runnable {
            override fun run() {
                Log.e("TAG", "파싱 중 입니다.")




                handler.postDelayed(this, 1000)
                //1초마다 수행

            }
        }//백그라운드 작업을 위한 handler 변수


        fun bind(item : MyWtList?) {
            Glide.with(itemView.context)
                .load(item?.wtImg)
                .into(binding.imgUserIcon)

            if (item != null) {
                binding.webTitle.text = item.wtTitle
            }
            //작가 제목

            binding.deleteButton.setOnClickListener{
                if (item != null) {
                    mCallback.deleteList(item)
                }
            }//클릭시 이벤트

            var on : Boolean? = item?.wtOn

            if(on == true){
                binding.alarmSwitch.isChecked = true
                //Log.e("TAG", "체크되어 있는 상태입니다.")
            }
            //알람이 체크되어있는 경우 체크 상태로 표시

            binding.alarmSwitch.setOnCheckedChangeListener{CompoundButton, onSwitch ->

                if(onSwitch){

                    /*
                    val searchWorkRequest = OneTimeWorkRequestBuilder<AlarmWorker>().build()
                    //일회성 작업
                    WorkManager.getInstance(itemView.context).enqueue(searchWorkRequest)
                    val handler = Handler(Looper.getMainLooper())
                    */

                    handler.post(runnable)
                    //핸들러 객체를 이용하여 파싱 구현





                    if (item != null) {

                        var updateList = MyWtList(item.id,item.wtTitle,item.wtImg,true)
                        mCallback.updateList(updateList)
                        Toast.makeText(itemView.context,"알림 ON",Toast.LENGTH_SHORT).show()

                    }//스위치 상태 업데이트

                }
                else{

                    handler.removeCallbacks(runnable)
                    Toast.makeText(itemView.context,"알림 OFF",Toast.LENGTH_SHORT).show()
                    //백그라운드 종료하는 코드 구현



                    if (item != null) {
                        mCallback.updateList(MyWtList(item.id,item.wtTitle,item.wtImg,false))
                    }//스위치 상태 업데이트

                }

            }//클릭시 알람 이벤트

        }

        private fun callAlarm() {

            val intent = Intent(itemView.context, MainActivity::class.java)

            val notificationManager = itemView.context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val notificationID = Random.nextInt()
            //랜덤으로 ID 생성

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                createNotificationChannel(notificationManager)
            }

            val pendingIntent =
                PendingIntent.getActivity(itemView.context, 0, intent, PendingIntent.FLAG_IMMUTABLE)

            val notification = NotificationCompat.Builder(itemView.context, Constants.CHANNEL_ID)
                .setContentTitle("알람 시작")
                .setContentText("웹툰이 업데이트 되었습니다..")
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.alarm)
                .setContentIntent(pendingIntent)
                .build()

            notificationManager.notify(notificationID, notification)

        }//알람 구현

    }





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