package org.techtown.nw_alarmer.parsingClass

import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import org.techtown.nw_alarmer.JsoupCrawlerExample
import org.techtown.nw_alarmer.WTData

class Parsing {

    private val webToonUrl = "https://comic.naver.com/webtoon/weekday"
    //웹툰 url

    public fun doTask(day : Int , list : ArrayList<WTData>) : ArrayList<WTData> {

        Log.e("TAG", day.toString())

        list.clear()

        val scope = GlobalScope

        scope.launch {

            //SSL 체크
            if (webToonUrl.indexOf("https://") >= 0) {
                JsoupCrawlerExample.setSSL();
            }//https:로 시작하는경우 setSSL() 실행하여 우회

            val doc = Jsoup.connect(webToonUrl).get()
            //HTML 가져오기

            val saturdayList = doc.select("div.col_inner")[day].select("li")
            //요일 전체 목록 가져오기

            for (e in saturdayList) {

                var wtIntel = e.select("img")//한 웹툰의 정보들

                var title = ""
                var img = ""
                var up = ""

                var upIntel = e.select("em")
                //업데이트 정보

                for (j in wtIntel) {

                    title = j.absUrl("title").replace("https://comic.naver.com/webtoon/", "")
                    //  Log.e("TAG", title)

                }//웹툰 제목 가져오기

                for (j in wtIntel) {

                    img = j.absUrl("src")
                    //Log.e("TAG", img)

                }//웹툰 img 가져오기

                for(j in upIntel){
                    up = j.absUrl("class").replace("https://comic.naver.com/webtoon/", "")
                }//웹툰 up 정보 가져오기

                if(up.equals("ico_updt"))
                    up = "Up"
                else if(up.equals("ico_break"))
                    up = "휴재"

                list.add(WTData(img,title,up))
            }


        }//비동기 적용

        return list
    }


}