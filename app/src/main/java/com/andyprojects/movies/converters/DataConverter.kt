package com.andyprojects.movies.converters

import androidx.room.TypeConverter
import com.andyprojects.movies.network.NetworkModelConfig
import com.google.gson.Gson

class DataConverter {
    private val gson = Gson()

    @TypeConverter
    fun List<String>?.toJsonString(): String = gson.toJson(this)

    @TypeConverter
    fun String?.toList(): List<String>
        = gson.fromJson(this, listOf<String>()::class.java)

    @TypeConverter
    fun NetworkModelConfig.ImagesConfig?.toJsonString(): String = gson.toJson(this)

    @TypeConverter
    fun String?.toImagesConfig(): NetworkModelConfig.ImagesConfig
            = gson.fromJson(this, NetworkModelConfig.ImagesConfig::class.java)
}