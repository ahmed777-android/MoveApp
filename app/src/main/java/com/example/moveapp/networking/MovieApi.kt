package com.example.moveapp.networking


import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/popular")
    suspend fun fetchPopularMoviesAsync()
            : Response<MoviesResponse>

    @GET("movie/popular")
    suspend fun getPopularMovie(
        @Query("page") page: Int? = null
    ): MoviesResponse


    @GET("movie/{movie_id}/recommendations")
    suspend fun getSimilarMovie(
        @Path("movie_id") movie_id: Number
    )
            : Response<MoviesResponse>

    @GET("movie/{movie_id}/reviews")
    fun getReviewsMovie(
        @Path("movie_id") movie_id: Number
    )
            : Response<ReviewsListResponseSchema>

    @GET("search/movie")
    fun fetchMovieByQueryAsync(@Query("query") query: String)
            : Response<MoviesResponse>
}


