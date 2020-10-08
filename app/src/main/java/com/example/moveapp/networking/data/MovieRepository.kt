package com.example.moveapp.networking.data


import com.example.moveapp.networking.MovieApi
import javax.inject.Inject

class MovieRepository @Inject constructor(private val moviesService:MovieApi){
    suspend fun getPopularRepo(page :Int)=moviesService.getPopularMovie(page)

}