package com.andyprojects.movies.movies.pagedFragments.popular

import com.andyprojects.movies.BuildConfig
import com.andyprojects.movies.movies.MoviesViewModel
import com.andyprojects.movies.network.MoviesNetwork
import kotlinx.coroutines.launch

class PopularViewModel: MoviesViewModel() {
    val result = super.response
    init {
        if(response.value == null)
            this.getResponse()
    }
    override fun getResponse() {
        coroutineScope.launch {
            val responseDiffered = MoviesNetwork.retrofitService
                .getPopularMoviesAsync("en", 1, BuildConfig.API_KEY)
            try {
                val responseString = responseDiffered.await()
                if(responseString.isNotEmpty())
                    _response.value = responseString
            } catch(t: Throwable) {
                _response.value = t.message
            }
        }
    }
}