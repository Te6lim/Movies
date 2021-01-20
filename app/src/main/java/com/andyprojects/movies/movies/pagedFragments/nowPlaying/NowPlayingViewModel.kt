package com.andyprojects.movies.movies.pagedFragments.nowPlaying

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.andyprojects.movies.BuildConfig
import com.andyprojects.movies.network.MoviesNetwork
import kotlinx.coroutines.*

class NowPlayingViewModel: ViewModel() {

    private var _response = MutableLiveData<String>()
    val response: LiveData<String>
    get() = _response

    private val viewModelJob = Job()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getResponse()
    }

    private fun getResponse() {
        coroutineScope.launch {
            val responseDiffered = MoviesNetwork.retrofitService
                .getMoviesAsync("en", 1, BuildConfig.API_KEY)
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