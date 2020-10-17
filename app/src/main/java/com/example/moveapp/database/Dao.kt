package com.example.moveapp.database

import androidx.room.*
import androidx.room.Dao
import com.example.moveapp.networking.data.Movie

@Dao
interface Dao {
    @Query("SELECT * FROM Movie")
    fun getAll():List<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(item: Movie)

    @Delete
    suspend fun delete(item: Movie)
}