package com.andyprojects.movies.configuration

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Configuration(
    val images: Images?,
    val logo_sizes: List<String?>?,
    val poster_sizes: List<String?>?,
    val profile_sizes: List<String?>?,
    val still_sizes: List<String?>?,
    val change_keys: List<String?>?
): Parcelable {

    @Parcelize
    data class Images(
        val base_url: String?,
        val secure_base_url: String?,
        val backdrop_sizes: List<String?>?
    ): Parcelable
}