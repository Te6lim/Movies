package com.andyprojects.movies.movies

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CastPerson(
    val adult: Boolean,
    val gender: Int?,
    val id: Int?,
    val known_for_department: String?,
    val name: String?,
    val original_name: String?,
    val popularity: Double?,
    val profile_path: String?,
    val cast_id: Int?,
    val character: String?,
    val credit_id: String?
): Parcelable

@Parcelize
data class CrewPerson(
    val adult: Boolean,
    val gender: Int?,
    val id: Int?,
    val known_for_department: String?,
    val name: String?,
    val original_name: String?,
    val popularity: Double?,
    val profile_path: String?,
    val credit_id: String?,
    val department: String?,
    val job: String?
): Parcelable

data class Credits(
    val id: Int?,
    val cast: List<CastPerson?>?,
    val crew: List<CrewPerson?>?
)