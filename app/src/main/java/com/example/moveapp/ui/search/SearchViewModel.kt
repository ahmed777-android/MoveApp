package com.example.moveapp.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.moveapp.networking.MovieRepository
import com.example.moveapp.networking.data.ResultSearch
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchViewModel @Inject constructor(private  val repository:MovieRepository):ViewModel(){


    private val currentQuery = MutableLiveData(DEFAULT_QUERY)

    fun searchMovies(query: String) {
        currentQuery.value = query
    }

    val movies=currentQuery.switchMap {
        repository.getSearchResults(it).cachedIn(viewModelScope)
    }

    companion object {
        private const val DEFAULT_QUERY = "a"
    }

}
/*   private var currentQueryValue: String? = null
   private var currentSearchResult: Flow<PagingData<ResultSearch>>? = null
   suspend fun searchRepo(queryString: String): Flow<PagingData<ResultSearch>> {
       val lastResult = currentSearchResult
       if (queryString == currentQueryValue && lastResult != null) {
           return lastResult
       }
       currentQueryValue = queryString
       val newResult: Flow<PagingData<ResultSearch>> = repository.getSearchResultStream(queryString)
           .cachedIn(viewModelScope)
       currentSearchResult = newResult
       return newResult
   }*/