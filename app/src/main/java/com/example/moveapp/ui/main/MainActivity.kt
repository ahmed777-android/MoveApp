package com.example.moveapp.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moveapp.MyApplication
import com.example.moveapp.R
import com.example.moveapp.ui.adapter.PopularAdapter
import com.example.moveapp.ui.details.DetailsActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() ,PopularAdapter.MovieItemListener {
    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: MainViewModel
    private  lateinit var  adapter:PopularAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MyApplication.application.appComponent.inject(this)
        setUi()
        setupViewModel()
        setupObservers()
    }
    private  fun  setUi(){
        adapter = PopularAdapter(this,this)
        rv.layoutManager=LinearLayoutManager(this)
        rv.adapter=adapter
        adapter.addLoadStateListener {
            if (it.refresh==LoadState.Loading){
                progress_bar.visibility=View.VISIBLE
            }else{
                progress_bar.visibility=View.INVISIBLE
            }
        }
    }
    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory)[MainViewModel::class.java]
    }
    private fun setupObservers(){
        lifecycleScope.launch {
            viewModel.flow.collectLatest{
                adapter.submitData(pagingData =it )
            }
        }
    }
    override fun onItemClick(movieId: Int) {
     val   intent=Intent(this,DetailsActivity::class.java)
        intent.putExtra("id",movieId)
        startActivity(intent)
    }
}