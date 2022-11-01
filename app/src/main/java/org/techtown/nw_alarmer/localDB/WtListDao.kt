package org.techtown.nw_alarmer.localDB

import androidx.lifecycle.LiveData
import androidx.room.*
import org.techtown.nw_alarmer.WTData
import java.util.concurrent.Flow

@Dao
interface WtListDao {

    @Query("Select * FROM  MyWtList ")
    fun getList() : LiveData<List<MyWtList>>
    //가져오기

    @Delete
    fun deleteList(list : MyWtList)
    //삭제

    // OnConflictStrategy.IGNORE = 동일한 아이디가 있을 시 무시
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertList(myWtList: MyWtList)
    //삽입

    @Update
    fun updateList(myWtList: MyWtList)
    //업데이트


}