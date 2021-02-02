package com.andyprojects.movies.movies

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.andyprojects.movies.BuildConfig
import com.andyprojects.movies.network.MoviesNetworkStatus
import kotlinx.coroutines.*

class MoviesDataSourceFactory(
    private val getMoviesAsync:(String, Int, String) -> Deferred<Movies>,
    private val networkStatus: MutableLiveData<MoviesNetworkStatus>,
    private var hasSomeLoaded: (Boolean) -> Unit
): DataSource.Factory<Int, Movie>() {

    override fun create(): DataSource<Int, Movie> {
        return MoviesDataSource(getMoviesAsync, networkStatus, hasSomeLoaded)
    }

    class MoviesDataSource(
        private val getMoviesAsync:(String, Int, String) -> Deferred<Movies>,
        private val networkStatus: MutableLiveData<MoviesNetworkStatus>,
        private var hasSomeLoaded: (Boolean) -> Unit
    ): PageKeyedDataSource<Int, Movie>() {

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
                networkStatus.value = MoviesNetworkStatus.LOADING
                val responseDeferred = getMoviesAsync("en", KEY, BuildConfig.API_KEY)
                try {
                    val response = responseDeferred.await()
                    if(response.results != null)
                        callback.onResult(response.results, null, ++KEY)
                    networkStatus.value = MoviesNetworkStatus.DONE
                    hasSomeLoaded(true)
                } catch(t: Throwable) { networkStatus.value = MoviesNetworkStatus.ERROR }
            }
        }

        override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
            coroutineScope.launch {
                networkStatus.value = MoviesNetworkStatus.LOADING
                val responseDiffered = getMoviesAsync("en", params.key, BuildConfig.API_KEY)
                try {
                    val response = responseDiffered.await()
                    val key = if(params.key > 1) params.key - 1
                    else null
                    if(response.results != null)
                        callback.onResult(response.results, key)
                    networkStatus.value = MoviesNetworkStatus.DONE
                } catch(t: Throwable) { networkStatus.value = MoviesNetworkStatus.ERROR }
            }
        }

        override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
            coroutineScope.launch {
                networkStatus.value = MoviesNetworkStatus.LOADING
                val responseDeferred = getMoviesAsync("en", params.key, BuildConfig.API_KEY)
                try {
                    val response = responseDeferred.await()
                    val totalPages = response.total_pages ?: 0
                    val key = if(params.key < totalPages) params.key + 1
                    else null
                    if(response.results != null)
                        callback.onResult(response.results, key)
                    networkStatus.value = MoviesNetworkStatus.DONE
                } catch(t: Throwable) { networkStatus.value = MoviesNetworkStatus.ERROR }
            }
        }
    }
}