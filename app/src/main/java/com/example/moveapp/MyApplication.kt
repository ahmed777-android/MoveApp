package com.example.moveapp

import android.app.Application
import com.example.moveapp.di.ApplicationComponent
import com.example.moveapp.di.DaggerApplicationComponent
import com.example.moveapp.di.module.AppModule
import com.example.moveapp.di.module.NetworkingModule
import com.example.moveapp.ui.ViewModelModule

class MyApplication : Application() {
     lateinit var appComponent: ApplicationComponent

    companion object {
        @get:Synchronized
        lateinit var application: MyApplication
            private set
    }
    override fun onCreate() {
        super.onCreate()
        application = this
        appComponent = initAppComponent(this)

    }


    private fun initAppComponent(app: MyApplication): ApplicationComponent {
        return DaggerApplicationComponent.builder()
            .appModule(AppModule(app)).networkingModule(NetworkingModule())
            .build()
    }


}