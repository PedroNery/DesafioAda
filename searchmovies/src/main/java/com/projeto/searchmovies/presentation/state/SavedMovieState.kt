package com.projeto.searchmovies.presentation.state

import com.projeto.common.base.intent.UIState
import com.projeto.searchmovies.presentation.dataui.MovieItemDataUI

data class SavedMovieState(
    val showLoading: Boolean = true,
    val movieList: List<MovieItemDataUI>? = listOf()
) : UIState {

    fun showContent(movieList: List<MovieItemDataUI>?) : SavedMovieState {
        return this.copy(
            showLoading = false,
            movieList = movieList
        )
    }

}