package org.techtown.nw_alarmer.localDB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.techtown.nw_alarmer.WTData

@Entity(tableName = "MyWtList")
data class MyWtList(
    @PrimaryKey(autoGenerate = true) var id : Long,
    //key Id, update를 위한 id field 값 추가

    @ColumnInfo(name = "wtTitle")
    var wtTitle : String?,

    @ColumnInfo(name = "wtImg")
    var wtImg : String?,

    @ColumnInfo(name = "wtOn")
    val wtOn : Boolean?
    //파싱이 수행 중인지 확인하는 val


)