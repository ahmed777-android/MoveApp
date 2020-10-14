package com.example.moveapp.di

import android.content.Context
import com.example.moveapp.di.module.AppModule
import com.example.moveapp.di.module.DatabaseModule
import com.example.moveapp.di.module.NetworkingModule
import com.example.moveapp.di.module.ViewModelModule
import com.example.moveapp.ui.details.DetailsActivity
import com.example.moveapp.ui.main.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkingModule::class, ViewModelModule::class,DatabaseModule::class])
interface  ApplicationComponent {
    fun inject(activity: MainActivity)
    fun inject(activity: DetailsActivity)


}