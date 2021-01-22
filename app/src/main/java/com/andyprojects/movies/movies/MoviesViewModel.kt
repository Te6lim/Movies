package com.andyprojects.movies.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

abstract class MoviesViewModel: ViewModel() {

    protected var _response = MutableLiveData<String>()
    protected val response: LiveData<String>
        get() = _response

    private val viewModelJob = Job()
    protected val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    abstract fun getResponse()

    override fun onCleared() {
        viewModelJob.cancel()
        super.onCleared()
    }
}