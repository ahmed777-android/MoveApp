package com.example.moveapp.di

import com.example.moveapp.di.module.AppModule
import com.example.moveapp.di.module.NetworkingModule
import com.example.moveapp.ui.ViewModelModule
import com.example.moveapp.ui.details.DetailsActivity
import com.example.moveapp.ui.main.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class,NetworkingModule::class, ViewModelModule::class])
interface  ApplicationComponent {
    fun inject(activity: MainActivity)
    fun inject(activity: DetailsActivity)
}