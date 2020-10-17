package com.example.moveapp.ui.main


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.moveapp.networking.MovieRepository
import com.example.moveapp.networking.MoviesDataSource
import javax.inject.Inject


class MainViewModel @Inject constructor(private val repository: MovieRepository) : ViewModel() {

    val flow = Pager(PagingConfig(pageSize = 20))
    {
        Log.d("MainActivity", "pagger: ")
        MoviesDataSource(repository)
    }.flow.cachedIn(viewModelScope)


}