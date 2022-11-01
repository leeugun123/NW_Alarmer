package org.techtown.nw_alarmer.parsingClass

import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import org.techtown.nw_alarmer.JsoupCrawlerExample
import org.techtown.nw_alarmer.WTData
import org.techtown.nw_alarmer.localDB.MyWtList

class Parsing {

    private val webToonUrl = "https://comic.naver.com/webtoon/weekday"
    //웹툰 url

   fun doTask(day : Int , list : ArrayList<WTData>) : ArrayList<WTData> {

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

            val dayList = doc.select("div.col_inner")[day].select("li")
            //요일 전체 목록 가져오기

            for (e in dayList) {

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

    fun serach(myTitle : String) : Boolean {

        var upCheck = false

        //비동기 처리
        val scope = GlobalScope

        scope.launch {

            //SSL 체크
            if (webToonUrl.indexOf("https://") >= 0) {
                JsoupCrawlerExample.setSSL();
            }//https:로 시작하는경우 setSSL() 실행하여 우회

            val doc = Jsoup.connect(webToonUrl).get()
            //HTML 가져오기

            for(i in 0..6){

                val dayList = doc.select("div.col_inner")[i].select("li")
                //요일 전체 목록 가져오기

                for (e in dayList) {

                    var wtIntel = e.select("img")//한 웹툰의 정보들

                    var title = ""
                    var up = ""

                    var upIntel = e.select("em")
                    //업데이트 정보

                    for (j in wtIntel) {

                        title = j.absUrl("title").replace("https://comic.naver.com/webtoon/", "")
                        //  Log.e("TAG", title)
                    }//웹툰 제목 가져오기

                    for(j in upIntel){
                        up = j.absUrl("class").replace("https://comic.naver.com/webtoon/", "")
                    }//웹툰 up 정보 가져오기

                    if(title.equals(myTitle) && up.equals("ico_updt")){

                        upCheck = true
                        break
                    }
                }

                if(upCheck) {
                    break
                }

            }

        }//비동기 적용

        Handler().postDelayed({
            Log.e("TAG", "지연하겠습니다"+upCheck.toString())
        }, 3000)
        //잠시동안 지연

        Log.e("TAG", "실제 반환되는 값"+upCheck.toString())
        return upCheck
    }


}