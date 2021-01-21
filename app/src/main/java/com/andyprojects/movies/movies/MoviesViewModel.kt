package com.andyprojects.movies.movies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

abstract class MoviesViewModel: ViewModel() {

    protected var _response = MutableLiveData<String>()

    private val viewModelJob = Job()
    protected val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    abstract fun getResponse()
}