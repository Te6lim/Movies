package com.andyprojects.movies

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.andyprojects.movies.database.ConfigDb
import com.andyprojects.movies.repository.ConfigRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

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

    class MainViewModelFactory(private val app: Application): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(MainViewModel::class.java))
                return MainViewModel(app) as T
            throw IllegalArgumentException("class is not assignable")
        }
    }
}