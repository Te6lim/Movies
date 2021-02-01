package com.andyprojects.movies.movies.topRated

import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.andyprojects.movies.movies.MoviesDataSourceFactory
import com.andyprojects.movies.movies.MoviesViewModel
import com.andyprojects.movies.network.MoviesNetwork
import com.andyprojects.movies.network.MoviesNetworkStatus

class TopRatedViewModel: MoviesViewModel() {

    private val _status = MutableLiveData<MoviesNetworkStatus>()

    init {
        if(response.value == null)
            getResponse()
    }
    override fun getResponse() {
        val moviesDataSourceFactory =
            MoviesDataSourceFactory(MoviesNetwork.retrofitService::getTopRatedMoviesAsync, _status)
        val config = PagedList.Config.Builder()
            .setPageSize(20)
            .setEnablePlaceholders(false)
            .build()
        response = LivePagedListBuilder(moviesDataSourceFactory, config)
            .build()
    }
}