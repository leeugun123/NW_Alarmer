package org.techtown.nw_alarmer.localDB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import org.techtown.nw_alarmer.WTData

@Dao
interface WtListDao {

    @Query("Select * From  MyWtList")
    fun getList() : LiveData<List<MyWtList>>
    //가져오기

    @Query("DELETE From MyWtList")
    fun deleteList()
    //삭제

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(myWtList: MyWtList)
    //삽입

}