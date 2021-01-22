package com.andyprojects.movies.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.andyprojects.movies.configuration.Configuration

@Entity(tableName = "movies_config_table")
class DatabaseConfig(
    @ColumnInfo(name="images")
    var images: Configuration.Images,

    @ColumnInfo(name="logo_sizes")
    var logo_sizes: List<String>,

    @ColumnInfo(name="poster_sizes")
    var poster_sizes: List<String?>,

    @ColumnInfo(name="profile_sizes")
    var profile_sizes: List<String>,

    @ColumnInfo(name="still_sizes")
    var still_sizes: List<String>,

    @ColumnInfo(name="change_keys")
    var change_keys: List<String>
)