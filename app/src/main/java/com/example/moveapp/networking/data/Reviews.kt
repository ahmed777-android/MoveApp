package com.example.moveapp.networking.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Reviews(
    val author: String,
    val content: String,
    val id: String,
    val url: String
)