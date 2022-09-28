package org.techtown.nw_alarmer.localDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [MyWtList::class],
    version = 2,
    exportSchema = true
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
                        .fallbackToDestructiveMigration()//기존 데이터를 버리고 다음 버전으로 넘어감
                        .build()
                }

            }

            return instance
        }

    }

}