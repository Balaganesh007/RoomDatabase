package com.example.roomdatabase.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DataDao {

    @Insert
    fun insert (model: Model)

    @Query ("SELECT * FROM Names_Database ORDER BY id DESC")
    fun getAllData() : LiveData<List<Model>>

    @Query("DELETE FROM Names_Database ")
    fun deleteAllData()

}