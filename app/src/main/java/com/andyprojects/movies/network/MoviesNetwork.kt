package com.andyprojects.movies.network

import com.andyprojects.movies.screens.movies.Movies
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

enum class MoviesNetworkStatus {
    LOADING, ERROR, DONE;
}

const val BASE_URL = "https://api.themoviedb.org/3/"
const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface MoviesApiService {
    @GET("movie/now_playing")
    fun getNowPlayingMoviesAsync(
        @Query("language") lang: String,
        @Query("page") page: Int,
        @Query("api_key") key: String
    ): Deferred<Movies>

    @GET("movie/popular")
    fun getPopularMoviesAsync(
        @Query("language") lang: String,
        @Query("page") page: Int,
        @Query("api_key") key: String
    ): Deferred<Movies>

    @GET("movie/top_rated")
    fun getTopRatedMoviesAsync(
        @Query("language") lang: String,
        @Query("page") page: Int,
        @Query("api_key") key: String
    ): Deferred<Movies>

    @GET("movie/latest")
    fun getLatestMoviesAsync(
        @Query("language") lang: String,
        @Query("page") page: Int,
        @Query("api_key") key: String
    ): Deferred<Movies>

    @GET("movie/upcoming")
    fun getUpComingMoviesAsync(
        @Query("language") lang: String,
        @Query("page") page: Int,
        @Query("api_key") key: String
    ): Deferred<Movies>

    @GET("configuration")
    fun getConfigAsync(
        @Query("api_key") key: String
    ): Deferred<NetworkModelConfig>
}

object MoviesNetwork {
    val retrofitService: MoviesApiService by lazy {
        retrofit.create(MoviesApiService::class.java)
    }
}