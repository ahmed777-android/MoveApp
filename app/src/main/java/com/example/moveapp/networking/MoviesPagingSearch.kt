package com.example.moveapp.networking

import androidx.paging.PagingSource
import com.bumptech.glide.load.HttpException
import com.example.moveapp.networking.data.ResultSearch
import java.io.IOException

private const val STARTING_PAGE_INDEX = 1

class MoviesPagingSearch(private val service: MovieApi, private val query: String) :
    PagingSource<Int, ResultSearch>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResultSearch> {
        val position = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = service.searchResult(query,position)
            val repos = response.items
            LoadResult.Page(
                data = repos,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (repos.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }

    }
}