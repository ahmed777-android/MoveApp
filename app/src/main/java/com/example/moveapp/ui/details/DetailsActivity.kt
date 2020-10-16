package com.example.moveapp.ui.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.moveapp.MyApplication
import com.example.moveapp.R
import com.example.moveapp.networking.data.Cast
import com.example.moveapp.networking.data.Movie
import com.example.moveapp.networking.data.SimilarSchema
import com.example.moveapp.ui.adapter.CastAdapter
import com.example.moveapp.ui.adapter.RecommendAdapter
import com.example.moveapp.utilit.Status
import kotlinx.android.synthetic.main.activity_details.*
import javax.inject.Inject

class DetailsActivity : AppCompatActivity() {
    private val TAG = "DetailsActivity"
    private var movieId: Int? = null
    private lateinit var castAdapter: CastAdapter
    private lateinit var recommendAdapter: RecommendAdapter

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: DetailsViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        MyApplication.application.appComponent.inject(this)
        movieId = intent.getIntExtra("id", 0)
        setup()
        setupObserversofMovies()
        setObserversOfSimilar()
        setObserversOfCast()
    }

    private fun setup() {
        viewModel = ViewModelProviders.of(this, viewModelFactory)[DetailsViewModel::class.java]
    }
    private fun setupObserversofMovies() {
        movieId?.let { it ->
            viewModel.getMovie(id = it).observe(this, {
                it.let {
                    when (it.status) {
                        Status.SUCCESS -> {
                            it.data?.let { movie -> setData(movie) }
                            Log.d(TAG, "setupObserversofMovies1: ${it.data?.backdrop_path}")
                        }
                        Status.LOADING -> {
                        }
                        Status.ERROR -> {
                            Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                            Log.d(TAG, "setupObserversofMovies2: ${it.message}")
                        }
                    }
                }
            })
        }
    }

    private fun setObserversOfSimilar() {
        movieId?.let {
            viewModel.getSimilar(it).observe(this, {
                it.let {
                    when (it.status) {
                        Status.SUCCESS -> {
                            it.data?.let { it -> setSimilar(it.results) }
                        }
                        Status.LOADING -> {
                        }
                        Status.ERROR -> {
                            Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                            Log.d(TAG, "setObserversOfSimilar:  ${it.message}")
                        }
                    }
                }
            })
        }
    }
    private fun setObserversOfCast() {
        movieId?.let {
            viewModel.getCat(it).observe(this, {
                it.let {
                    when (it.status) {
                        Status.SUCCESS -> {
                            it.data?.let { it -> setCast(it.cast) }
                        }
                        Status.LOADING -> {
                        }
                        Status.ERROR -> {
                            Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                            Log.d(TAG, "setObserversOfCast: ${it.message}")
                        }
                    }
                }
            })
        }
    }
    private fun setData(movie: Movie) {
        movie.let {
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500${it.poster_path}")
                .into(poster)
            name.text = it.original_title
            rat.rating = (it.vote_average?.div(2))!!
            tagline.text = it.tagline
        }
    }
    private fun setCast(list: List<Cast>) {
        castAdapter = CastAdapter(this)
        rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rv.adapter = castAdapter
        castAdapter.submitList(list)
    }

    private fun setSimilar(list: List<SimilarSchema.Result>) {
        recommendAdapter = RecommendAdapter(this)
        rv_comment.layoutManager=LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rv_comment.adapter=recommendAdapter
        recommendAdapter.submitList(list)
    }

}