package com.andyprojects.movies.movies

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val poster_path: String?,
    val adult: Boolean,
    val overview: String?,
    val release_date: String?,
    val genre_ids: List<Int?>?,
    val id: Int?,
    val original_title: String?,
    val original_language: String?,
    val title: String?,
    val backdrop_path: String?,
    val vote_count: Int?,
    val video: Boolean,
    val vote_average: Double?
): Parcelable

@Parcelize
data class Movies(
    val page: Int?,
    val total_pages: Int?,
    val total_results: Int?,
    val results: List<Movie?>?,
    val dates: Dates?
): Parcelable {
    @Parcelize
    class Dates(
        val maximum: String?,
        val minimum: String?
    ): Parcelable
}