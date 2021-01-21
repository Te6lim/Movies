package com.andyprojects.movies.movies.pagedFragments.topRated

import androidx.lifecycle.LiveData
import com.andyprojects.movies.BuildConfig
import com.andyprojects.movies.movies.MoviesViewModel
import com.andyprojects.movies.network.MoviesNetwork
import kotlinx.coroutines.launch

class TopRatedViewModel: MoviesViewModel() {
    val response: LiveData<String>
        get() =_response

    init {
        getResponse()
    }

    override fun getResponse() {
        coroutineScope.launch {
            val responseDiffered = MoviesNetwork.retrofitService
                .getTopRatedMoviesAsync("en", 1, BuildConfig.API_KEY)
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