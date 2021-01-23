package com.andyprojects.movies.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.andyprojects.movies.BuildConfig
import com.andyprojects.movies.network.asDatabaseModel
import com.andyprojects.movies.database.ConfigDb
import com.andyprojects.movies.database.asDomainModel
import com.andyprojects.movies.domain.Config
import com.andyprojects.movies.network.MoviesNetwork
import com.andyprojects.movies.network.asDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ConfigRepository (private val db: ConfigDb) {

    val readyConfig: LiveData<Config> = Transformations
        .map (db.configDbDao.getConfig()) {
        it.asDomainModel()
    }

    suspend fun refreshConfig() {
        val configDiffered = MoviesNetwork.retrofitService
            .getConfigAsync(BuildConfig.API_KEY)
        withContext(Dispatchers.IO) {
            try {
                val config = configDiffered.await()
                db.configDbDao.insertAll(config.asDatabaseModel())
            } catch(t: Throwable) { }
        }
    }
}