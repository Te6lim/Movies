package com.andyprojects.movies.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.andyprojects.movies.BuildConfig
import com.andyprojects.movies.network.asDatabaseModel
import com.andyprojects.movies.database.ConfigDb
import com.andyprojects.movies.database.asDomainModel
import com.andyprojects.movies.domain.Config
import com.andyprojects.movies.network.MoviesNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ConfigRepository private constructor(app: Application) {

    private val db = ConfigDb.getDatabase(app)
    companion object {
        var configRepo: ConfigRepository? = null
        fun getConfigRepo(app: Application): ConfigRepository {
            if(configRepo == null){
                configRepo = ConfigRepository(app)

            }
            return configRepo as ConfigRepository
        }
    }

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
            } catch(t: Throwable) {
            }
        }
    }
}