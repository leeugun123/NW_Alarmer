package org.techtown.nw_alarmer.DayFragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import org.techtown.nw_alarmer.JsoupCrawlerExample

import org.techtown.nw_alarmer.R
import org.techtown.nw_alarmer.RecyclerWtAdapter
import org.techtown.nw_alarmer.WTData
import org.techtown.nw_alarmer.databinding.FragmentMondayBinding

class MondayFragment : Fragment() {

    private var mBinding : FragmentMondayBinding? = null

    private val webToonlist : ArrayList<WTData> = ArrayList()
    lateinit var recyclerView : RecyclerView

    private val webToonUrl = "https://comic.naver.com/webtoon/weekday"
    //웹툰 url

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        val binding = FragmentMondayBinding.inflate(layoutInflater)
        mBinding = binding

        doTask()

        /*
        webToonlist.add(WTData("외모지상주의"))
        webToonlist.add(WTData("안드로메다"))
        webToonlist.add(WTData("내 딸 내놔!"))
        */


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_monday,container,false)

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
            if(webToonUrl.indexOf("https://") >= 0){
                JsoupCrawlerExample.setSSL();
            }//https:로 시작하는경우 setSSL() 실행하여 우회

            val doc = Jsoup.connect(webToonUrl).get()
            //HTML 가져오기

            val mondayList = doc.select("div.col_inner")[0].select("li")
            //월요일꺼 전체 목록 가져오기

            for(e in mondayList){

                var wtIntel = e.select("img")//한 웹툰의 정보들

                for(j in wtIntel){

                    var title = j.absUrl("title").replace("https://comic.naver.com/webtoon/","")
                    Log.e("TAG",title)
                    webToonlist.add(WTData(title))

                }//웹툰 제목 가져오기



            }





        }//비동기 적용

    }



}