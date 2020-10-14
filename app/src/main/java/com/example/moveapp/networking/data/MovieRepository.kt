package com.example.moveapp.networking.data


import com.example.moveapp.networking.MovieApi
import javax.inject.Inject

class MovieRepository @Inject constructor(private val moviesService:MovieApi){
    suspend fun getPopularRepo(page :Int)=moviesService.getPopularMovie(page)
    suspend fun getMovieWithId(id:Number)=moviesService.getMovieById(id)
    suspend fun getRecommendations(id:Number)=moviesService.getSimilarMovie(id)
    suspend fun getReviews(id:Number)=moviesService.getSimilarMovie(id)
    suspend fun getCast(id:Number)=moviesService.getCast(id)

}