package org.techtown.nw_alarmer.localDB

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class WTViewModel (application: Application) : ViewModel(){

    private val repository = WTRepository(application)
    private val wtLists = repository.getAll()

    fun getAll() : LiveData<List<MyWtList>>{
        return wtLists
    }

    fun insert(myList : MyWtList){
        repository.insert(myList)
    }

   /*
    fun delete(myList : MyWtList){
        repository.delete(myList)
    }
    */

}