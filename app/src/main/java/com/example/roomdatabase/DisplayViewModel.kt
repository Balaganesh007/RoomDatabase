package com.example.roomdatabase

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.roomdatabase.database.DataDao
import com.example.roomdatabase.database.Model
import kotlinx.coroutines.*

class DisplayViewModel (
    private val dataSource:DataDao,
    application: Application):
    AndroidViewModel(application){

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    var allname : LiveData<List<Model>>? = dataSource.getAllData()

//    fun getData() {
//        uiScope.launch {
//            ge()
//        }
//    }
//
//    private suspend fun ge() {
//
//        uiScope.launch {
//            withContext(Dispatchers.IO) {
//                val allNamesInDataBase = dataSource.getAllData()
//                allname = allNamesInDataBase
//            }
//        }
//    }

    fun onClear(){
        uiScope.launch {
            deleteAll()
        }
    }
    private fun deleteAll(){
        uiScope.launch {
            clear()
            allname = null
        }
    }
    private suspend fun clear(){
        withContext(Dispatchers.IO){
            dataSource.deleteAllData()
        }
    }
}