package org.techtown.nw_alarmer.localDB

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.techtown.nw_alarmer.WTData

@Entity
data class MyWtList(

    var wtTitle : String,
    var wtImg : String,
    var upImg : String,

){
    @PrimaryKey(autoGenerate = true) var id : Int = 0
}
