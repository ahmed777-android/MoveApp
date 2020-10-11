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
import com.example.moveapp.networking.data.Cast
import kotlinx.android.synthetic.main.item_cast.view.*

class CastAdapter(private val context: Context) :
    ListAdapter<Cast, CastAdapter.ViewHolder>(DiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.item_cast, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: Cast = getItem(position)
        holder.bind(item, context)
    }
    inner class ViewHolder(V: View) : RecyclerView.ViewHolder(V) {
        private val _image = V.image_cast
        private val _name = V.name_cast
        fun bind(item: Cast, context: Context) {
            with(item) {
                Glide.with(context).load("https://image.tmdb.org/t/p/w500$profilePath")
                    .into(_image)
                _name.text = name
            }
        }
    }
    class DiffCallback : DiffUtil.ItemCallback<Cast>() {
        override fun areItemsTheSame(oldItem: Cast, newItem: Cast) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Cast, newItem: Cast) =
            areItemsTheSame(oldItem, newItem)
    }
}
