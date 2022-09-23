package org.techtown.nw_alarmer.localDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [MyWtList::class],
    version = 1,
    exportSchema = false
)

abstract class ListDatabase : RoomDatabase() {

    abstract fun wtListDao() : WtListDao

    companion object{

        private var instance : ListDatabase? = null

        @Synchronized
        fun getInstance(context : Context) : ListDatabase? {

            if(instance == null){

                synchronized(ListDatabase::class){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ListDatabase::class.java,
                        "list-database"
                    )
                        .allowMainThreadQueries()//메인 쓰레드에서 작동 허용
                        .build()
                }

            }

            return instance
        }

    }

}