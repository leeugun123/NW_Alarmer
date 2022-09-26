package org.techtown.nw_alarmer.localDB

import android.app.Application
import androidx.lifecycle.LiveData
import org.techtown.nw_alarmer.WTData

class WTRepository (application: Application){

    private val wtlistDao : WtListDao
    private val wtList : LiveData<List<MyWtList>>

    init {

        var db = ListDatabase.getInstance(application)

        wtlistDao = db!!.wtListDao()
        wtList = db.wtListDao().getList()

    }

    fun insert(list : MyWtList){
        wtlistDao.insertList(list)
    }

    fun getAll(): LiveData<List<MyWtList>> {
        return wtlistDao.getList()
    }

    /*
     fun delete(list : MyWtList) {
         wtlistDao.deleteList(list)
     }

     */
      //삭제 기능 나중에 구현해야함.



}