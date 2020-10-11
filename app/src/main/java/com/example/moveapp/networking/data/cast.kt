package com.example.moveapp.networking.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Cast(
    @Json(name = "cast_id")
    val castId: Int?,
    val character: String?,
    @Json(name = "credit_id")
    val creditId: String?,
    val gender: Int,
    val id: Int,
    val name: String?,
    val order: Int?,
    @Json(name = "profile_path")
    val profilePath: String?
)