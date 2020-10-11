package com.example.moveapp.networking.data


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CastSchema(
    val cast: List<Cast>,
    val crew: List<Crew>,
    val id: Int
)