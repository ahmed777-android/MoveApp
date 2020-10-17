package com.example.moveapp.di.module

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
 class AppModule (private val movieApplication: Application) {
    @Provides
    @Singleton
    fun provideApplication()= movieApplication
}