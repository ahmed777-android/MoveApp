package com.example.moveapp.networking


import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.moveapp.networking.data.ResultSearch
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class MovieRepository @Inject constructor(private val remoteDataSource: MovieApi) {
    suspend fun getPopularRepo(page: Int) = remoteDataSource.getPopularMovie(page)
    suspend fun getMovieWithId(id: Number) = remoteDataSource.getMovieById(id)
    suspend fun getRecommendations(id: Number) = remoteDataSource.getSimilarMovie(id)
    suspend fun getReviews(id: Number) = remoteDataSource.getReviewsMovie(id)
    suspend fun getCast(id: Number) = remoteDataSource.getCast(id)

    suspend fun getSearchResultStream(query: String): Flow<PagingData<ResultSearch>> {
        Log.d("MovieRepository", "New query: $query")
        return Pager(config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = { MoviesPagingSearch(remoteDataSource, query) }).flow
    }
    fun getSearchResults(query:String)=
        Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { MoviesPagingSearch(remoteDataSource, query) }
        ).liveData}