package org.techtown.nw_alarmer.DayFragment

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import org.techtown.nw_alarmer.*
import org.techtown.nw_alarmer.databinding.FragmentFridayBinding
import kotlin.random.Random


class FridayFragment : Fragment() {

    private var mBinding : FragmentFridayBinding? = null

    private val webToonlist : ArrayList<WTData> = ArrayList()
    lateinit var recyclerView : RecyclerView

    private val webToonUrl = "https://comic.naver.com/webtoon/weekday"
    //웹툰 url

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        val binding = FragmentFridayBinding.inflate(layoutInflater)
        mBinding = binding

        doTask()

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_friday,container,false)

        recyclerView = rootView.findViewById(R.id.lstUser) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = RecyclerWtAdapter(webToonlist,requireContext())

        return rootView
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }

    private fun doTask() {

        webToonlist.clear()

        val scope = GlobalScope

        scope.launch {

            //SSL 체크
            if (webToonUrl.indexOf("https://") >= 0) {
                JsoupCrawlerExample.setSSL();
            }//https:로 시작하는경우 setSSL() 실행하여 우회

            val doc = Jsoup.connect(webToonUrl).get()
            //HTML 가져오기

            val FridayList = doc.select("div.col_inner")[4].select("li")
            //화요일 전체 목록 가져오기

            for (e in FridayList) {

                var wtIntel = e.select("img")//한 웹툰의 정보들

                var title = ""
                var img = ""
                var up = ""

                var upIntel = e.select("em")
                //업데이트 정보

                for (j in wtIntel) {

                    title = j.absUrl("title").replace("https://comic.naver.com/webtoon/", "")
                    //Log.e("TAG", title)

                }//웹툰 제목 가져오기

                for (j in wtIntel) {

                    img = j.absUrl("src")
                    //Log.e("TAG", img)

                }//웹툰 img 가져오기

                for(j in upIntel){
                    up = j.absUrl("class").replace("https://comic.naver.com/webtoon/", "")
                }//웹툰 up 정보 가져오기

                if(up.equals("ico_updt")){


                    up = "Up"
                }

                else if(up.equals("ico_break"))
                    up = "휴재"


                webToonlist.add(WTData(img,title,up))
            }


        }//비동기 적용

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