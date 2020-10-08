package com.example.moveapp.ui.main


import androidx.lifecycle.ViewModel
import androidx.paging.*
import com.example.moveapp.networking.data.MovieRepository
import com.example.moveapp.ui.adapter.MoviesDataSource
import javax.inject.Inject
import androidx.lifecycle.viewModelScope


class MainViewModel @Inject constructor(private val repository: MovieRepository) : ViewModel() {

    val flow = Pager(PagingConfig(pageSize = 20, enablePlaceholders = false))
    { MoviesDataSource(repository) }.flow.cachedIn(viewModelScope)




}