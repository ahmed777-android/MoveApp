package com.example.moveapp.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.moveapp.MyApplication
import com.example.moveapp.R
import com.example.moveapp.networking.data.Movie
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    var arrat= mutableListOf<Movie?>()
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MyApplication.application.appComponent.inject(this)
    //   viewModel= ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)

    }
}