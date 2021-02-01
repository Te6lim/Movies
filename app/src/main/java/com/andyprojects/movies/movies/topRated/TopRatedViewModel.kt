package com.andyprojects.movies.movies.topRated

import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.andyprojects.movies.movies.MoviesDataSourceFactory
import com.andyprojects.movies.movies.MoviesViewModel
import com.andyprojects.movies.network.MoviesNetwork

class TopRatedViewModel: MoviesViewModel() {
    init {
        getResponse()
    }

    override fun getResponse() {
        val moviesDataSourceFactory =
            MoviesDataSourceFactory(MoviesNetwork.retrofitService::getTopRatedMoviesAsync, stat)
        val config = PagedList.Config.Builder()
            .setPageSize(20)
            .setEnablePlaceholders(false)
            .build()
        response = LivePagedListBuilder(moviesDataSourceFactory, config)
            .build()
    }
}