package com.andyprojects.movies.movies.page.topRated

/*class TopRatedViewModel: MoviesViewModel() {
    val result = super.response
    init {
        if(response.value == null)
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
}*/