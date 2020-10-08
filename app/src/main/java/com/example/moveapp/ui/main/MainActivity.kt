package com.example.moveapp.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moveapp.MyApplication
import com.example.moveapp.R
import com.example.moveapp.ui.adapter.PopularAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: MainViewModel
    private  lateinit var  adpter:PopularAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MyApplication.application.appComponent.inject(this)
        setUi()
        setupViewModel()
        setupObservers()
    }
    private  fun  setUi(){
        adpter = PopularAdapter(this)
        rv.layoutManager=LinearLayoutManager(this)
        rv.adapter=adpter
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
    }
    private fun setupObservers(){
        lifecycleScope.launch {
            viewModel.flow.collectLatest{
                adpter.submitData(pagingData =it )

            }
        }
    }


  /*  private fun setupObservers(page:Int) {
        viewModel.getPopular(page).observe(this, {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        resource.data?.let {
                            progress_bar.visibility= View.GONE

                        }
                    }
                    Status.ERROR -> {
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        progress_bar.visibility= View.VISIBLE



                    }
                }
            }
        })
    }
*/
}