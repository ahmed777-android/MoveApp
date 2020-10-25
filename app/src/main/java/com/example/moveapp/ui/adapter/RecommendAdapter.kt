package com.example.moveapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moveapp.R

import com.example.moveapp.networking.data.SimilarSchema
import kotlinx.android.synthetic.main.item_review.view.*

class RecommendAdapter(private val context:Context,private val listener:MovieItemListener) :
    ListAdapter<SimilarSchema.Result, RecommendAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.item_review, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: SimilarSchema.Result = getItem(position)
        holder.bind(item,context)
        holder.itemView.setOnClickListener {
            listener.onItemClick(item.id)
        }
    }

    inner class ViewHolder(V: View) : RecyclerView.ViewHolder(V) {
        private val _name = V.name
        private val _image = V.image_recommend



        fun bind(item: SimilarSchema.Result,context:Context) {
            with(item){
                Glide.with(context).load("https://image.tmdb.org/t/p/w500$posterPath")
                    .into(_image)
                _name.text = title
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<SimilarSchema.Result>() {
        override fun areItemsTheSame(oldItem: SimilarSchema.Result, newItem: SimilarSchema.Result) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: SimilarSchema.Result, newItem: SimilarSchema.Result) =
            areItemsTheSame(oldItem, newItem)
    }
    interface MovieItemListener {
        fun onItemClick(movieId: Int)
    }
}