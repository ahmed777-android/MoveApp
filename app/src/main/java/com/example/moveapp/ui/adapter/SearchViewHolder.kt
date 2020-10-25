package com.example.moveapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moveapp.R
import com.example.moveapp.networking.data.ResultSearch
import kotlinx.android.synthetic.main.item_view.view.*

/**
 * View Holder for a [ResultSearch] RecyclerView list item.
 */
class SearchViewHolder(private val view:View) : RecyclerView.ViewHolder(view) {
    private var result: ResultSearch? = null
    private val poster: ImageView = view.m_poster
    private val name: TextView = view.m_name
   // private val rating: RatingBar = view.rating

    fun bind(item:ResultSearch?){
        if (item!=null){

            this.result = item
            name.text=item.title
       //    rating.rating = (item.voteAverage?.div(2)?.toFloat()!!)
            Glide.with(view.context).load("https://image.tmdb.org/t/p/w500${item.posterPath}")
                .into(poster)
        }
    }

    companion object {
        fun create(parent: ViewGroup): SearchViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_view, parent, false)
            return SearchViewHolder(view)
        }
    }
}