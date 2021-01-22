package com.andyprojects.movies.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.andyprojects.movies.configuration.Configuration

@Dao
interface ConfigDbDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(configs: Configuration)

    @Query("SELECT * from movies_config_table LIMIT 1")
    fun getConfig(): LiveData<Configuration>
}