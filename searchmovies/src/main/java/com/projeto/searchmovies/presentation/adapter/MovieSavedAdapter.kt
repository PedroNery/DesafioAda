package com.projeto.searchmovies.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.projeto.common.base.viewbinding.viewBinding
import com.projeto.searchmovies.R
import com.projeto.searchmovies.databinding.MovieItemListBinding
import com.projeto.searchmovies.databinding.SearchMovieItemListBinding
import com.projeto.searchmovies.presentation.dataui.MovieItemDataUI

private const val TYPE_SEARCH = 0
private const val TYPE_MOVIE = 1

internal class MovieSavedAdapter(
    private val searchItemClick: () -> Unit,
    private val movieItemClick: (movieId: String) -> Unit
) : ListAdapter<MovieItemDataUI, RecyclerView.ViewHolder>(MovieListDillCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            TYPE_SEARCH -> {
                SearchViewHolder(layoutInflater.inflate(
                    R.layout.search_movie_item_list, parent, false
                ))
            }
            else -> {
                MovieViewHolder(layoutInflater.inflate(
                    R.layout.movie_item_list, parent, false
                ))
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position).type) {
            0 -> TYPE_SEARCH
            else -> TYPE_MOVIE
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is SearchViewHolder -> {
                holder.bind(getItem(position))
            }
            is MovieViewHolder -> {
                holder.bind(getItem(position), movieItemClick)
            }
        }
    }

    inner class SearchViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        private val binding: SearchMovieItemListBinding by viewBinding()

        fun bind(item: MovieItemDataUI) {
            binding.cvLayout.setOnClickListener {
                searchItemClick()
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