package com.example.moveapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.example.moveapp.ui.adapter.LoadStateAdapter.LoadStateViewHolder
import androidx.recyclerview.widget.RecyclerView
import com.example.moveapp.R
import kotlinx.android.synthetic.main.movie_state_load_footer.view.*

class LoadStateAdapter(private val retry: () -> Unit) : LoadStateAdapter<LoadStateViewHolder>() {
    inner class LoadStateViewHolder(private val binding: View) : RecyclerView.ViewHolder(binding) {
        init {
            binding.button_retry.setOnClickListener {
                retry.invoke()
            }
        }

        fun bind(loadState: LoadState) {
            binding.apply {
                progress_bar.isVisible = loadState is LoadState.Loading
                button_retry.isVisible = loadState !is LoadState.Loading
                text_view_error.isVisible = loadState !is LoadState.Loading

            }
        }
    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return LoadStateViewHolder(
            inflater.inflate(
                R.layout.movie_state_load_footer,
                parent,
                false
            )
        )
    }
}