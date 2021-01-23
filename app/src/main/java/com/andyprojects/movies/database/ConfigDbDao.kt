package com.andyprojects.movies.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ConfigDbDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(configs: DbModelConfig)

    @Query("SELECT * from movies_config_table LIMIT 1")
    fun getConfig(): LiveData<DbModelConfig>
}