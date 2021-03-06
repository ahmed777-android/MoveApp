package com.example.moveapp.networking


import com.example.moveapp.networking.data.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {


    @GET("movie/popular")
    suspend fun getPopularMovie(
        @Query("page") page: Int? = null
    ): MovieResult

    @GET("movie/{movie_id}")
    suspend fun getMovieById(
        @Path("movie_id") movie_id: Number
    ): Movie

    @GET("movie/{movie_id}/recommendations")
    suspend fun getSimilarMovie(
        @Path("movie_id") movie_id: Number
    ): SimilarSchema

    @GET("movie/{movie_id}/reviews")
    suspend fun getReviewsMovie(
        @Path("movie_id") movie_id: Number
    ): ReviewsListResponseSchema

    @GET("movie/{movie_id}/credits")
    suspend fun getCast(
        @Path("movie_id") movie_id: Number
    ): CastSchema


    @GET("search/movie")
    suspend fun searchResult(@Query("query") query: String  ,@Query("page") page: Int?= null): MovieSearchResponse
}


