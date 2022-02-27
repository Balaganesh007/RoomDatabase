package com.example.roomdatabase.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Model::class], version = 1, exportSchema = false)
abstract class StoreNameDataBase : RoomDatabase() {

    abstract val dataDao : DataDao

    companion object {

        @Volatile
        private var INSTANCE: StoreNameDataBase? = null

        fun getInstance(context: Context): StoreNameDataBase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        StoreNameDataBase::class.java,
                        "Names_Database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
