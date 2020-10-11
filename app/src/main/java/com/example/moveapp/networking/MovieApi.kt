package com.example.moveapp.networking


import com.example.moveapp.networking.data.Cast
import com.example.moveapp.networking.data.CastSchema
import com.example.moveapp.networking.data.Movie
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {


    @GET("movie/popular")
    suspend fun getPopularMovie(
        @Query("page") page: Int? = null
    ): MoviesResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieById(
        @Path("movie_id") movie_id: Number
    ): Movie

    @GET("movie/{movie_id}/recommendations")
    suspend fun getSimilarMovie(
        @Path("movie_id") movie_id: Number
    ): MoviesResponse

    @GET("movie/{movie_id}/reviews")
    suspend fun getReviewsMovie(
        @Path("movie_id") movie_id: Number
    ): ReviewsListResponseSchema

    @GET("movie/{movie_id}/credits")
    suspend fun getCast(
        @Path("movie_id") movie_id: Number
    ): CastSchema


    @GET("search/movie")
    suspend fun fetchMovieByQueryAsync(@Query("query") query: String): MoviesResponse
}


