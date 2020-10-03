package com.example.moveapp.networking

import com.example.moveapp.networking.data.Movie
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface MovieApi {


    @GET("movie/popular")
    fun fetchPopularMoviesAsync():MoviesResponse

    @GET("movie/popular")
    suspend fun getPopularMovie(
        @QueryMap pageId: Map<String, Int>
    ): Response<MoviesResponse>




    @GET("movie/{movie_id}/recommendations")
    suspend fun getSimilarMovie(
        @Path("movie_id") movie_id: Number
    ): Response<MoviesResponse>

    @GET("movie/{movie_id}/reviews")
     fun getReviewsMovie(
        @Path("movie_id") movie_id: Number): Response<ReviewsListResponseSchema>
    @GET("search/movie")
    fun fetchMovieByQueryAsync(@Query("query") query: String): Response<MoviesResponse>
}


