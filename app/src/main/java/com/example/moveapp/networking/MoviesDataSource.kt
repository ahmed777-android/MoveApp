package com.example.moveapp.networking

import android.util.Log
import androidx.paging.PagingSource
import com.example.moveapp.networking.data.MovieResult

class MoviesDataSource(private val repository: MovieRepository) :
    PagingSource<Int, MovieResult.ResultPopuler>() {

    private val TAG = "MainActivity MoviesDataSource"

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieResult.ResultPopuler> {
        return try {

            val nextPage = params.key ?: 1
            val response = repository.getPopularRepo(nextPage)
            //  Log.d(TAG, "load: ")
            LoadResult.Page(
                data = response.results,
                prevKey = null, // Only paging forward.
                nextKey = response.page?.plus(1)
            )
        } catch (e: Exception) {
            // Handle errors in this block
            Log.d(TAG, "load: catch ${e.message}")
            LoadResult.Error(e)
        }

    }

}