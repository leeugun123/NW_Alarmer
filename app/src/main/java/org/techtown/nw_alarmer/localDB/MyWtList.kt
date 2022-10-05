package org.techtown.nw_alarmer.localDB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.techtown.nw_alarmer.WTData

@Entity(tableName = "MyWtList")
data class MyWtList(

    @ColumnInfo(name = "wtTitle")
    var wtTitle : String?,

    @ColumnInfo(name = "wtImg")
    var wtImg : String?,

    @ColumnInfo(name = "wtOn")
    val wtOn : Boolean?

    //?는 null 값을 허용함을 의미
){
    @PrimaryKey(autoGenerate = true) var id : Int = 0
}
