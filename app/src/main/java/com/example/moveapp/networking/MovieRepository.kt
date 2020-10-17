package com.example.moveapp.networking


import com.example.moveapp.database.Dao
import javax.inject.Inject

class MovieRepository @Inject constructor(private val remoteDataSource: MovieApi,private val localDataSource: Dao){
    suspend fun getPopularRepo(page :Int)=remoteDataSource.getPopularMovie(page)
    suspend fun getMovieWithId(id:Number)=remoteDataSource.getMovieById(id)
    suspend fun getRecommendations(id:Number)=remoteDataSource.getSimilarMovie(id)
    suspend fun getReviews(id:Number)=remoteDataSource.getReviewsMovie(id)
    suspend fun getCast(id:Number)=remoteDataSource.getCast(id)
}