package com.andyprojects.movies.screens.movies.nowPlaying

import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.andyprojects.movies.screens.movies.MoviesDataSourceFactory
import com.andyprojects.movies.screens.movies.MoviesViewModel
import com.andyprojects.movies.network.MoviesNetwork

class NowPlayingViewModel: MoviesViewModel() {

    init {
        getResponse()
    }
    override fun getResponse() {
        val moviesDataSourceFactory =
            MoviesDataSourceFactory(
                MoviesNetwork.retrofitService::getNowPlayingMoviesAsync, stat, this::hasSomeLoaded.setter
            )
        val config = PagedList.Config.Builder()
            .setPageSize(20)
            .setEnablePlaceholders(false)
            .build()
        response = LivePagedListBuilder(moviesDataSourceFactory, config)
            .build()
    }
}