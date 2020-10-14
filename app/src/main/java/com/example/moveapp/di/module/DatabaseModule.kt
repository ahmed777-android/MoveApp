package com.example.moveapp.di.module

import android.app.Application
import com.example.moveapp.database.MovieDao
import com.example.moveapp.database.MovieDatabase
import com.example.moveapp.networking.MovieApi
import com.example.moveapp.networking.MovieRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule(private  val app:Application) {

    @Singleton
    @Provides
    fun provideDatabase() = MovieDatabase.getDatabase(app)

    @Singleton
    @Provides
    fun provideCharacterDao(db: MovieDatabase) = db.getMovieDao()

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: MovieApi, localDataSource: MovieDao) =
        MovieRepository(remoteDataSource, localDataSource)
}