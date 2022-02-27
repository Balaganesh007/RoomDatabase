package com.example.roomdatabase.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Names_Database")
data class Model(

    @PrimaryKey(autoGenerate = true)
    val Id: Long = 0L,

    @ColumnInfo(name = "Names")
    val Data: String
)