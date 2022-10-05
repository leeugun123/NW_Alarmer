package org.techtown.nw_alarmer.localDB

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.ComputableLiveData
import androidx.recyclerview.widget.RecyclerView
import androidx.work.*
import com.bumptech.glide.Glide
import org.techtown.nw_alarmer.BackgroundAlarmWorker.AlarmWorker
import org.techtown.nw_alarmer.Constants
import org.techtown.nw_alarmer.MainActivity
import org.techtown.nw_alarmer.R
import org.techtown.nw_alarmer.databinding.MywtViewBinding
import org.techtown.nw_alarmer.databinding.WtViewBinding
import java.util.concurrent.TimeUnit
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

    fun setList(list : List<MyWtList>){
        items.clear()
        items.addAll(list)
    }

    //  각 항목에 필요한 기능을 구현
    inner class ViewHolder(private val binding : MywtViewBinding) : RecyclerView.ViewHolder(binding.root) {

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


            binding.alarmSwitch.setOnCheckedChangeListener{CompoundButton, onSwitch ->


                if(onSwitch){

                    Toast.makeText(itemView.context,"알림을 설정 ON",Toast.LENGTH_SHORT).show()

                    val uploadWorkRequest = PeriodicWorkRequestBuilder<AlarmWorker>(24, TimeUnit.HOURS)
                        /*
                        .setConstraints(Constraints.Builder()
                            .setRequiresCharging(true) //충전하고 있을 때 실행
                            .build()
                        ) */
                        // Additional configuration
                            .build()
                    //일회성 작업

                    WorkManager.getInstance(itemView.context).enqueueUniquePeriodicWork("sendLogs",
                        ExistingPeriodicWorkPolicy.KEEP,uploadWorkRequest)



                }
                else{


                    Toast.makeText(itemView.context,"알림 설정 OFF",Toast.LENGTH_SHORT).show()
                }




            }//클릭시 알람 이벤트





        }

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