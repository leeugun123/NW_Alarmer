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
    //var upImg : String?,

){
    @PrimaryKey(autoGenerate = true) var id : Int = 0
}
