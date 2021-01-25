package com.andyprojects.movies.movies.pagedFragments.nowPlaying
import com.andyprojects.movies.BuildConfig
import com.andyprojects.movies.movies.MoviesViewModel
import com.andyprojects.movies.network.MoviesNetwork
import kotlinx.coroutines.*

class NowPlayingViewModel: MoviesViewModel() {
    init {
        if(response.value == null)
            getResponse()
    }
    override fun getResponse() {
        coroutineScope.launch {
            val responseDiffered = MoviesNetwork.retrofitService
                .getNowPlayingMoviesAsync("en", 1, BuildConfig.API_KEY)
            try {
                val response = responseDiffered.await()
                if(response.results != null)
                    mutableResponse.value = response.results
            } catch(t: Throwable) {}
        }
    }
}