package com.example.moveapp.networking

import androidx.paging.PagingSource
import com.example.moveapp.networking.data.Movie

class MoviesDataSource  ( private val repository: MovieRepository) : PagingSource<Int, Movie>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val nextPage = params.key ?: 1
            val response=repository.getPopularRepo(nextPage)
            LoadResult.Page(
                data = response.movies,
                prevKey = null , // Only paging forward.
                nextKey = response.page?.plus(1)
            )
        } catch (e: Exception) {
            // Handle errors in this block
            LoadResult.Error(e)
        }

    }

}