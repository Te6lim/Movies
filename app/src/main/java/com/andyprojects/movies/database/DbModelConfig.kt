package com.andyprojects.movies.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.andyprojects.movies.converters.DataConverter
import com.andyprojects.movies.network.NetworkModelConfig
import com.andyprojects.movies.domain.Config

@Entity(tableName = "movies_config_table")
@TypeConverters(DataConverter::class)
class DbModelConfig(
    @PrimaryKey
    @ColumnInfo(name="images")
    var imagesConfig: NetworkModelConfig.ImagesConfig,

    @ColumnInfo(name="logo_sizes")
    var logo_sizes: List<String?>?,

    @ColumnInfo(name="poster_sizes")
    var poster_sizes: List<String?>?,

    @ColumnInfo(name="profile_sizes")
    var profile_sizes: List<String?>?,

    @ColumnInfo(name="still_sizes")
    var still_sizes: List<String?>?,

    @ColumnInfo(name="change_keys")
    var change_keys: List<String?>?
)

fun DbModelConfig.asDomainModel(): Config {
    return Config(
        imagesConfig = this.imagesConfig,
        logo_sizes = this.logo_sizes,
        poster_sizes = this.poster_sizes,
        profile_sizes = this.profile_sizes,
        still_sizes = this.still_sizes,
        change_keys = this.change_keys
    )
}