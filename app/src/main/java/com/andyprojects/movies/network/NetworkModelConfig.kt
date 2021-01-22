package com.andyprojects.movies.network

import android.os.Parcelable
import com.andyprojects.movies.database.DbModelConfig
import com.andyprojects.movies.domain.Config
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NetworkModelConfig(
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

fun NetworkModelConfig.asDatabaseModel(): DbModelConfig {
    return DbModelConfig(
        images = this.images,
        logo_sizes = this.logo_sizes,
        poster_sizes = this.poster_sizes,
        profile_sizes = this.profile_sizes,
        still_sizes = this.still_sizes,
        change_keys = this.change_keys
    )
}

fun NetworkModelConfig.asDomainModel(): Config {
    return Config(
        images = this.images,
        logo_sizes = this.logo_sizes,
        poster_sizes = this.poster_sizes,
        profile_sizes = this.profile_sizes,
        still_sizes = this.still_sizes,
        change_keys = this.change_keys
    )
}