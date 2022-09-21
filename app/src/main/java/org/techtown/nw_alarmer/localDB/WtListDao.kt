package org.techtown.nw_alarmer.localDB

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WtListDao {

    @Query("Select * From  MyWtList")
    fun getList() : MyWtList
    //가져오기

    @Query("DELETE From MyWtList")
    fun deleteList()
    //삭제

    @Insert
    fun insertList(myWtList: MyWtList)
    //삽입


}