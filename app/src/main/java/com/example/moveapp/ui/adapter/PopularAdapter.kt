package com.example.moveapp.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moveapp.R
import com.example.moveapp.networking.data.Movie
import com.example.moveapp.ui.adapter.PopularAdapter.*
import kotlinx.android.synthetic.main.item_view.view.*

class PopularAdapter(private val context: Context, private val itemListener: MovieItemListener) :
    PagingDataAdapter<Movie, PopularViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PopularViewHolder(inflater.inflate(R.layout.item_view, parent, false))
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        val itemData: Movie? = getItem(position)
        if (itemData != null) {
            holder.bind(itemData, context)
        }
        holder.itemView.setOnClickListener {
            itemData?.id?.let { it1 -> itemListener.onItemClick(it1) }
        }
    }

     class DiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Movie, newItem: Movie) =
            areItemsTheSame(oldItem, newItem)
    }

    inner class PopularViewHolder(V: View) : RecyclerView.ViewHolder(V) {
        private val poster: ImageView = V.m_poster
        private val name: TextView = V.m_name
        private val rating: RatingBar = V.rating

        fun bind(movie: Movie, context: Context) {
            with(movie) {
                Glide.with(context).load("https://image.tmdb.org/t/p/w500$posterPath")
                    .into(poster)
                name.text = title
                rating.rating = (voteAverage?.div(2))!!
            }
        }
    }

    interface MovieItemListener {
        fun onItemClick(movieId: Int)
    }
}
