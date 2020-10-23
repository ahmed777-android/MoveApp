package com.example.moveapp.ui.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moveapp.networking.data.ResultSearch

/**
 * Adapter for the list of movies search.
 */
class SearchAdapter : PagingDataAdapter<ResultSearch, RecyclerView.ViewHolder>(REPO_COMPARATOR) {
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
         val item = getItem(position  )
        if (item != null) {
            (holder as SearchViewHolder).bind(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SearchViewHolder.create(parent)
    }

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<ResultSearch>() {
            override fun areItemsTheSame(oldItem: ResultSearch, newItem: ResultSearch): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: ResultSearch, newItem: ResultSearch): Boolean =
                oldItem == newItem
        }
    }


}