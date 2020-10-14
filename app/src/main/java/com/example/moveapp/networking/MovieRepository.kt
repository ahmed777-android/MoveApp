package com.example.moveapp.networking


import com.example.moveapp.database.MovieDao
import com.example.moveapp.networking.MovieApi

class MovieRepository (private val remoteDataSource:MovieApi, localDataSource: MovieDao){
    suspend fun getPopularRepo(page :Int)=remoteDataSource.getPopularMovie(page)
    suspend fun getMovieWithId(id:Number)=remoteDataSource.getMovieById(id)
    suspend fun getRecommendations(id:Number)=remoteDataSource.getSimilarMovie(id)
    suspend fun getReviews(id:Number)=remoteDataSource.getReviewsMovie(id)
    suspend fun getCast(id:Number)=remoteDataSource.getCast(id)

}