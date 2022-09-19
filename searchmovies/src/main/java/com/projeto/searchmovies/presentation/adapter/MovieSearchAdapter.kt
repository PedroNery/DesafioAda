package com.projeto.searchmovies.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.projeto.common.base.viewbinding.viewBinding
import com.projeto.searchmovies.R
import com.projeto.searchmovies.databinding.MovieItemListBinding
import com.projeto.searchmovies.presentation.dataui.MovieItemDataUI

internal class MovieSearchAdapter(
    private val movieItemClick: (movie: MovieItemDataUI) -> Unit
) : ListAdapter<MovieItemDataUI, MovieSearchAdapter.MovieViewHolder>(MovieListDillCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MovieViewHolder(layoutInflater.inflate(
            R.layout.movie_item_list, parent, false
        ))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position), movieItemClick)
    }

    internal class MovieViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        private val binding: MovieItemListBinding by viewBinding()

        fun bind(item: MovieItemDataUI, movieItemCLick: (MovieItemDataUI) -> Unit) {
            binding.tvMovieTitle.text = item.title
            Glide.with(itemView.context)
                .load(item.poster)
                .centerCrop()
                .into(binding.ivMoviePoster)
            binding.cvLayout.setOnClickListener {
                movieItemCLick(item)
            }
        }
    }

    class MovieListDillCallback : DiffUtil.ItemCallback<MovieItemDataUI>() {
        override fun areItemsTheSame(oldItem: MovieItemDataUI, newItem: MovieItemDataUI): Boolean {
            return oldItem.imdbID == newItem.imdbID
        }

        override fun areContentsTheSame(oldItem: MovieItemDataUI, newItem: MovieItemDataUI): Boolean {
            return oldItem == newItem
        }

    }
}