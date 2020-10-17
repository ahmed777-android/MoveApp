package com.example.moveapp.di

import com.example.moveapp.di.module.*
import com.example.moveapp.ui.details.DetailsActivity
import com.example.moveapp.ui.main.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class,NetworkingModule::class,
    ViewModelModule::class,DataModule::class,
    RepositoryModule ::class])
interface  ApplicationComponent {
    fun inject(activity: MainActivity)
    fun inject(activity: DetailsActivity)
}