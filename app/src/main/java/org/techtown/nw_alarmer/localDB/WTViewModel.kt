package org.techtown.nw_alarmer.localDB

import android.app.Application
import androidx.lifecycle.*

class WTViewModel (application: Application) : AndroidViewModel(application){

    private val repository = WTRepository(application)
    private val wtLists = repository.getAll()

    fun getAll() : LiveData<List<MyWtList>>{
        return wtLists
    }

    fun insert(myList : MyWtList){
        repository.insert(myList)
    }

    fun delete(myList : MyWtList){
        repository.delete(myList)
    }




}