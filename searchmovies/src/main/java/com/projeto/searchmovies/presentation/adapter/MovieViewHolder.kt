package com.projeto.searchmovies.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.projeto.common.base.viewbinding.viewBinding
import com.projeto.searchmovies.R
import com.projeto.searchmovies.databinding.MovieItemListBinding
import com.projeto.searchmovies.presentation.dataui.MovieItemDataUI

internal class MovieViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView) {

    private val binding: MovieItemListBinding by viewBinding()

    fun bind(item: MovieItemDataUI, movieItemCLick: (String) -> Unit) {
        binding.tvMovieTitle.text = item.title
        Glide.with(itemView.context)
            .load(item.poster)
            .centerCrop()
            .placeholder(R.drawable.movie_placeholder)
            .into(binding.ivMoviePoster)
        binding.cvLayout.setOnClickListener {
            movieItemCLick(item.imdbID)
        }
    }
}