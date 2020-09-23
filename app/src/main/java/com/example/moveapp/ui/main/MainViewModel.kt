package com.example.moveapp.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moveapp.networking.data.Movie
import com.example.moveapp.networking.data.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor( private val repository: MovieRepository) :
    ViewModel()  {
    private val _popularMoviesLiveData = MutableLiveData<List<Movie>>()

     fun fetchPopularMovies() :MutableLiveData<List<Movie>>{
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val movies = repository.fetchPopularMovies()
                _popularMoviesLiveData.postValue(movies)
            } catch (e: Exception) {
            }
        }
         return _popularMoviesLiveData
    }

}