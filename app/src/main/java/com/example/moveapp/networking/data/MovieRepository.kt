package com.example.moveapp.networking.data


import android.util.Log
import com.example.moveapp.networking.MovieApi
import java.lang.Exception
import javax.inject.Inject

class MovieRepository @Inject constructor(private val moviesService:MovieApi){
    suspend fun  fetchPopularMovies():List<Movie>?{
        Log.d("TAG", "fetchPopularMovies")
        val deferredResponse=moviesService.fetchPopularMoviesAsync().await()
        return  if (deferredResponse.isSuccessful){
            deferredResponse.body()?.movies
        }else{
            throw  Exception()
        }
    }

}