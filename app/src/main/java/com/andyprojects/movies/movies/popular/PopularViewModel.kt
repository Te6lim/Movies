package com.andyprojects.movies.movies.popular

import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.andyprojects.movies.movies.MoviesDataSourceFactory
import com.andyprojects.movies.movies.MoviesViewModel
import com.andyprojects.movies.network.MoviesNetwork

class PopularViewModel: MoviesViewModel() {
    init {
        getResponse()
    }
    override fun getResponse() {
        val moviesDataSourceFactory =
            MoviesDataSourceFactory(MoviesNetwork.retrofitService::getPopularMoviesAsync, stat)
        val config = PagedList.Config.Builder()
            .setPageSize(20)
            .setEnablePlaceholders(false)
            .build()
        response = LivePagedListBuilder(moviesDataSourceFactory, config)
            .build()
    }
}