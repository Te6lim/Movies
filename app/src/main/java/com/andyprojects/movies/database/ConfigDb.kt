package com.andyprojects.movies.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DbModelConfig::class], version = 1, exportSchema = false)
abstract class ConfigDb: RoomDatabase() {

    abstract val configDbDao: ConfigDbDao

    companion object {
        @Volatile
        private var INSTANCE: ConfigDb? = null
        fun getDatabase(context: Context): ConfigDb {
            synchronized(this) {
                var instance = INSTANCE
                if(instance == null) {
                    instance = Room.databaseBuilder(context.applicationContext,
                        ConfigDb::class.java, "config_db_history")
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}