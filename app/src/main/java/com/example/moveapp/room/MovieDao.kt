package com.example.moveapp.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.moveapp.networking.data.Movie

@Dao
interface MovieDao {
    @Query("SELECT * FROM Movie")
     fun getAll():List<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(item: Movie)

    @Delete
    suspend fun delete(item: Movie)
}