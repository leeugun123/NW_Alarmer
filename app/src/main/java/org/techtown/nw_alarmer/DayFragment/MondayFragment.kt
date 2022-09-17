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
        list.add(WTData("박태준","외모지상주의"))
        list.add(WTData("이유건","안드로메다"))
        list.add(WTData("김부장","내 딸 내놔!"))
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

            val primaryWT = doc.select("div.col_inner").select("a.title")


            Log.e("TAG",primaryWT.html())



        }//비동기 적용

    }



}