package com.andyprojects.movies.movies

import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.andyprojects.movies.BuildConfig
import com.andyprojects.movies.network.MoviesNetwork
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MoviesDataSourceFactory: DataSource.Factory<Int, Movie>() {
    override fun create(): DataSource<Int, Movie> {
        return MoviesDataSource()
    }

    class MoviesDataSource: PageKeyedDataSource<Int, Movie>() {

        companion object {
            private var KEY = 1
        }

        private val dataSourceJob = Job()
        private val coroutineScope = CoroutineScope(dataSourceJob + Dispatchers.Main)

        override fun loadInitial(
            params: LoadInitialParams<Int>,
            callback: LoadInitialCallback<Int, Movie>
        ) {
            coroutineScope.launch {
                val responseDiffered = MoviesNetwork.retrofitService
                    .getNowPlayingMoviesAsync("en", KEY, BuildConfig.API_KEY)
                try {
                    val response = responseDiffered.await()
                    if(response.results != null)
                        callback.onResult(response.results, null, ++KEY)
                } catch(t: Throwable) {}
            }
        }

        override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
            coroutineScope.launch {
                val responseDiffered = MoviesNetwork.retrofitService
                    .getNowPlayingMoviesAsync("en", params.key, BuildConfig.API_KEY)
                try {
                    val response = responseDiffered.await()
                    val key = if(params.key > 1) params.key - 1
                    else null
                    if(response.results != null)
                        callback.onResult(response.results, key)
                } catch(t: Throwable) {}
            }
        }

        override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
            coroutineScope.launch {
                val responseDiffered = MoviesNetwork.retrofitService
                    .getNowPlayingMoviesAsync("en", params.key, BuildConfig.API_KEY)
                try {
                    val response = responseDiffered.await()
                    val totalPages = response.total_pages ?: 0
                    val key = if(params.key < totalPages) params.key + 1
                    else null
                    if(response.results != null)
                        callback.onResult(response.results, key)
                } catch(t: Throwable) {}
            }
        }
    }
}