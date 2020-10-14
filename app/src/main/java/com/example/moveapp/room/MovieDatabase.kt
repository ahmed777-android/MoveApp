package com.example.moveapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.moveapp.networking.data.Movie

@Database(entities = [Movie::class],version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun getMovieDao():MovieDao
}