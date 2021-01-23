package com.andyprojects.movies

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Review(
    val id: String?,
    val author: String?,
    val author_details: AuthorDetail?,
    val content: String?,
    val created_at: String?,
    val iso_639_1: String?,
    val media_id: Int?,
    val media_type: String?,
    val updated_at: String?,
    val url: String?
): Parcelable {
    @Parcelize
    class AuthorDetail(
        val name: String?,
        val username: String?,
        val avatar_path: String?,
        val rating: Int?
    ): Parcelable
}

@Parcelize
data class Reviews(
    val id: Int?,
    val page: Int?,
    val results: List<Review?>?,
    val total_pages: Int?,
    val total_results: Int?
): Parcelable