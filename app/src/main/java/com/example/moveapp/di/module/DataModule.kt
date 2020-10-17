package com.example.moveapp.di.module

import android.app.Application
import com.example.moveapp.database.Dao
import com.example.moveapp.database.MovieDatabase
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton


@Module
class DataModule {
    @Provides
    @Singleton
    fun provideApplicationDatabase(app: Application) = MovieDatabase.getDatabase(app)

    @Provides
    @Singleton
    fun provideUserDao(db: MovieDatabase) = db.getMovieDao()
}