package com.example.moveapp.networking.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class Crew(
    @Json(name = "credit_id")
    val creditId: String,
    val department: String,
    val gender: Int,
    val id: Int,
    val job: String,
    val name: String,
    @Json(name = "profile_path")
    val profilePath: String?
)