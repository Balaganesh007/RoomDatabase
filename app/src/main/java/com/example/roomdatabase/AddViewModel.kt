package com.example.roomdatabase

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.roomdatabase.database.DataDao
import com.example.roomdatabase.database.Model
import kotlinx.coroutines.*

class AddViewModel (
    private val dataSource: DataDao,
    application: Application
) : AndroidViewModel(application)  {

    private var _name = MutableLiveData<String>()
    val name : LiveData<String>
        get() = _name

    private val _navigateToDisplayFragment = MutableLiveData<Boolean?>()
    val navigateToDisplayFragment : LiveData<Boolean?>
        get() = _navigateToDisplayFragment

    fun setNavigation(value: Boolean) {
        _navigateToDisplayFragment.value = value
    }

    fun setName(name : String){
        _name.value = name
        onSaveButton()
    }

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun doneNavigation(){
        _navigateToDisplayFragment.value = null
    }
    private fun onSaveButton () {
        uiScope.launch{
            var newName = name
            insert(newName.value.toString())
        }
    }
    private suspend fun insert(newName: String) {
        withContext(Dispatchers.IO){
            dataSource.insert(Model(0,newName))
        }
    }
}