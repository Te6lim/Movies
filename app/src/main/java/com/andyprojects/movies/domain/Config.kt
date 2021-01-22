package com.andyprojects.movies.domain

import com.andyprojects.movies.network.NetworkModelConfig

data class Config(
    val images: NetworkModelConfig.Images?,
    val logo_sizes: List<String?>?,
    val poster_sizes: List<String?>?,
    val profile_sizes: List<String?>?,
    val still_sizes: List<String?>?,
    val change_keys: List<String?>?
)