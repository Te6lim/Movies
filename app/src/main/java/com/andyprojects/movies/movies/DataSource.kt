package com.andyprojects.movies.movies

import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.andyprojects.movies.BuildConfig
import kotlinx.coroutines.*

class MoviesDataSourceFactory(private val getMoviesAsync:(String, Int, String) -> Deferred<Movies>)
    : DataSource.Factory<Int, Movie>() {
    override fun create(): DataSource<Int, Movie> {
        return MoviesDataSource(getMoviesAsync)
    }

    class MoviesDataSource(private val getMoviesAsync:(String, Int, String) -> Deferred<Movies>)
        : PageKeyedDataSource<Int, Movie>() {

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
                val responseDeferred = getMoviesAsync("en", KEY, BuildConfig.API_KEY)
                try {
                    val response = responseDeferred.await()
                    if(response.results != null)
                        callback.onResult(response.results, null, ++KEY)
                } catch(t: Throwable) {}
            }
        }

        override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
            coroutineScope.launch {
                val responseDiffered = getMoviesAsync("en", params.key, BuildConfig.API_KEY)
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
                val responseDeferred = getMoviesAsync("en", params.key, BuildConfig.API_KEY)
                try {
                    val response = responseDeferred.await()
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