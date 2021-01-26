package com.andyprojects.movies

import android.app.Application
import com.andyprojects.movies.repository.ConfigRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MoviesApplication: Application() {

    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    override fun onCreate() {
        super.onCreate()
        val configRepository = ConfigRepository.getConfigRepo(this)
        coroutineScope.launch {
            configRepository.refreshConfig()
        }
    }
}