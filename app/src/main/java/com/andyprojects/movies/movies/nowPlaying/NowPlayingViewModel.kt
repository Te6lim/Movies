package com.andyprojects.movies.movies.nowPlaying
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.andyprojects.movies.movies.Movie
import com.andyprojects.movies.movies.MoviesDataSourceFactory
import com.andyprojects.movies.network.MoviesNetwork
import com.andyprojects.movies.network.MoviesNetworkStatus

class NowPlayingViewModel: ViewModel() {

    var response: LiveData<PagedList<Movie>> = MutableLiveData()
    private val _status = MutableLiveData<MoviesNetworkStatus>()
    val status: LiveData<MoviesNetworkStatus>
    get() = _status

    init {
        if(response.value == null)
            getResponse()
    }
    private fun getResponse() {
        val moviesDataSourceFactory =
            MoviesDataSourceFactory(MoviesNetwork.retrofitService::getNowPlayingMoviesAsync, _status)
        val config = PagedList.Config.Builder()
            .setPageSize(20)
            .setEnablePlaceholders(false)
            .build()
        response = LivePagedListBuilder(moviesDataSourceFactory, config)
            .build()
    }
}