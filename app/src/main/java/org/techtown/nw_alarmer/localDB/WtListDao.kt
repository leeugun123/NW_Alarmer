package org.techtown.nw_alarmer.localDB

import androidx.lifecycle.LiveData
import androidx.room.*
import org.techtown.nw_alarmer.WTData

@Dao
interface WtListDao {

    @Query("Select * From  MyWtList")
    fun getList() : LiveData<List<MyWtList>>
    //가져오기

    @Delete
    fun deleteList(list : MyWtList)
    //삭제

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(myWtList: MyWtList)
    //삽입

}