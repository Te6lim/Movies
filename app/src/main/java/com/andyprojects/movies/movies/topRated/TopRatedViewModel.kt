package com.andyprojects.movies.movies.topRated

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.andyprojects.movies.movies.Movie
import com.andyprojects.movies.movies.MoviesDataSourceFactory
import com.andyprojects.movies.network.MoviesNetwork

class TopRatedViewModel: ViewModel() {
    var response: LiveData<PagedList<Movie>> = MutableLiveData()

    init {
        if(response.value == null)
            getResponse()
    }
    private fun getResponse() {
        val moviesDataSourceFactory = MoviesDataSourceFactory(MoviesNetwork.retrofitService::getTopRatedMoviesAsync)
        val config = PagedList.Config.Builder()
            .setPageSize(20)
            .setEnablePlaceholders(false)
            .build()
        response = LivePagedListBuilder(moviesDataSourceFactory, config)
            .build()
    }
}