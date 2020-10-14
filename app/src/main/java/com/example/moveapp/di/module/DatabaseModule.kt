package com.example.moveapp.di.module

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.moveapp.networking.data.Movie
import com.example.moveapp.room.MovieDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule(private val context: Context) {
    private val mDBName = "test_database.db"

   @Singleton
   @Provides
   fun provideDatabase() :MovieDatabase = Room.databaseBuilder(context, MovieDatabase::class.java, mDBName).build()

    @Singleton
    @Provides
    fun providesDao(db:MovieDatabase)=db.getMovieDao()


}
