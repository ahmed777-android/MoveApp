package com.example.moveapp.ui.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moveapp.MyApplication
import com.example.moveapp.R
import com.example.moveapp.ui.adapter.LoadStateAdapter
import com.example.moveapp.ui.adapter.SearchAdapter
import kotlinx.android.synthetic.main.activity_search.*
import javax.inject.Inject

class SearchActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: SearchViewModel
    private  var adapter= SearchAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        title="Search"
        MyApplication.application.appComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[SearchViewModel::class.java]
        setUi()
       viewModel.movies.observe(this, Observer {
           adapter.submitData(lifecycle = this.lifecycle,it)
       })
    }



    private fun setUi() {
        Log.d("TAG", "setUi: ")
        list.layoutManager = LinearLayoutManager(this)
        list.adapter = adapter.withLoadStateHeaderAndFooter(
            header =LoadStateAdapter{adapter.retry()} ,
            footer = LoadStateAdapter{adapter.retry()}
        )

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView
        searchView.queryHint="Search Movie"
        searchView.setOnQueryTextListener(object  :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null){
                    list.scrollToPosition(0)
                    viewModel.searchMovies(query)
                    searchView.clearFocus()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean = true

        })
      return  super.onCreateOptionsMenu(menu)
    }
}



