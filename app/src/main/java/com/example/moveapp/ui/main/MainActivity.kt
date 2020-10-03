package com.example.moveapp.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.moveapp.MyApplication
import com.example.moveapp.R
import com.example.moveapp.networking.data.Movie
import com.example.moveapp.uti.Status
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MyApplication.application.appComponent.inject(this)
        setupViewModel()
        setupObservers()
    }
    private  fun setupViewModel(){
        viewModel=ViewModelProviders.of(this,viewModelFactory).get(MainViewModel::class.java)
    }
    private  fun setupObservers(){
        viewModel.getPopular().observe(this, {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                       resource.data?.let {
                               moviesResponse -> setData(moviesResponse.movies) }
                    }
                    Status.ERROR -> {
                        Log.d("TAG123", "setupObservers: "+it.message)
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        Log.d("TAG123", "setupObservers:2 ")

                    }
                }
            }
        })
    }
    private fun setData(data:List<Movie>?){
        Log.d("TAG123", "log: "+data?.size)

    }


}