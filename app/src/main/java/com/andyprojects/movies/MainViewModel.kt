package com.andyprojects.movies

import android.app.Application
import androidx.lifecycle.ViewModel
import com.andyprojects.movies.database.ConfigDb
import com.andyprojects.movies.repository.ConfigRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainViewModel(app: Application): ViewModel() {
    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val db = ConfigDb.getDatabase(app)
    private val configRepository = ConfigRepository(db)

    init {
        coroutineScope.launch {
            configRepository.refreshConfig()
        }
    }

    val config = configRepository.readyConfig

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}