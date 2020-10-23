package com.example.moveapp.networking.data

import com.squareup.moshi.Json

data class MovieSearchResponse(
    @Json(name = "results")val items: List<ResultSearch> = emptyList(),
    val page: Int?,
    @Json(name = "total_pages")
    val totalPages: Int?,
    @Json(name = "total_results")
    val totalResults: Int?
)