package com.example.moveapp.ui.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.moveapp.MyApplication
import com.example.moveapp.R
import com.example.moveapp.ui.ViewModelFactory
import javax.inject.Inject

class DetailsActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        MyApplication.application.appComponent.inject(this)
    }
}