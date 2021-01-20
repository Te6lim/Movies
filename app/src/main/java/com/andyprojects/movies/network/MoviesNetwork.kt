package com.andyprojects.movies.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://api.themoviedb.org/3/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface MoviesApiService {
    @GET("movie/now_playing")
    fun getMoviesAsync(
        @Query("language") lang: String,
        @Query("page") page: Int,
        @Query("api_key") key: String
    ): Deferred<String>
}

object MoviesNetwork {
    val retrofitService: MoviesApiService by lazy {
        retrofit.create(MoviesApiService::class.java)
    }
}