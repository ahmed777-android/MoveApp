package com.example.moveapp.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moveapp.MyApplication
import com.example.moveapp.R
import com.example.moveapp.ui.adapter.LoadStateAdapter
import com.example.moveapp.ui.adapter.PopularAdapter
import com.example.moveapp.ui.details.DetailsActivity
import com.example.moveapp.ui.search.SearchActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity(), PopularAdapter.MovieItemListener {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: PopularAdapter
    private val TAG :String= "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate: ")
        MyApplication.application.appComponent.inject(this)
        setUi()
        setupViewModel()
        setupObservers()
        fab.setOnClickListener {
            startActivity( Intent(this,SearchActivity::class.java))
        }
    }


    private fun setUi() {
        Log.d(TAG, "setUi: ")
        adapter = PopularAdapter(this, this)
        rv.layoutManager = LinearLayoutManager(this)
         rv.adapter = adapter.withLoadStateHeaderAndFooter(
                header = LoadStateAdapter{adapter.retry()} ,
        footer = LoadStateAdapter{adapter.retry()}
        )
        adapter.addLoadStateListener {
            if (it.refresh == LoadState.Loading) {
                progress_bar.visibility = View.VISIBLE
            } else {
                progress_bar.visibility = View.INVISIBLE
            }
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory)[MainViewModel::class.java]
    }

    private fun setupObservers() {
        lifecycleScope.launch {
            coroutineContext.let {
                Log.d(TAG, "setupObservers:coroutineContext  ")
            }
            viewModel.flow.collectLatest {
                adapter.submitData(pagingData = it)
            }
        }
        Log.d(TAG, "setupObservers:${adapter.itemCount} ")

    }

    override fun onItemClick(movieId: Int) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("id", movieId)
        startActivity(intent)
    }
}

