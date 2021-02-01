package com.andyprojects.movies.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.andyprojects.movies.network.MoviesNetworkStatus

abstract class MoviesViewModel: ViewModel() {
    var response: LiveData<PagedList<Movie>> = MutableLiveData()
    protected val stat = MutableLiveData<MoviesNetworkStatus>()
    val status: LiveData<MoviesNetworkStatus>
        get() = stat

    abstract fun getResponse()
}