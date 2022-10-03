package org.techtown.nw_alarmer.BackgroundAlarmWorker

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.work.*
import org.techtown.nw_alarmer.localDB.ListDatabase
import org.techtown.nw_alarmer.localDB.MyWtList
import org.techtown.nw_alarmer.localDB.WTViewModel
import org.techtown.nw_alarmer.localDB.WtListDao

class AlarmWorker(appContext: Context,params: WorkerParameters) : CoroutineWorker(appContext,params) {

    companion object{
        const val WORK_NAME = "Notification Work"
    }

    private val wtlistDao : WtListDao

    init {

        var db = ListDatabase.getInstance(appContext)
        wtlistDao = db!!.wtListDao()
    }//db가져오기 및 초기화


    override suspend fun doWork(): Result{

        try {

            var mylist : LiveData<List<MyWtList>> = wtlistDao.getList()

            //알람 가져오기 구현
            //Work

        }catch (e: Exception){
            Result.retry()
        }

        return Result.success()

    }

}