package com.example.moveapp.networking.data


import android.util.Log
import com.example.moveapp.networking.MovieApi
import java.lang.Exception
import javax.inject.Inject

class MovieRepository @Inject constructor(private val moviesService:MovieApi){
    suspend fun getPopularRepo()=moviesService.fetchPopularMoviesAsync()

}