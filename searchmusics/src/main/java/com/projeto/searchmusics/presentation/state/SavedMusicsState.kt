package com.projeto.searchmusics.presentation.state

import com.projeto.common.base.intent.UIState

data class SavedMovieState(
    val showLoading: Boolean = true,
    val movieList: List<String>? = listOf()
) : UIState {

    fun showContent(movieList: List<String>?) : SavedMovieState {
        return this.copy(
            showLoading = false,
            movieList = movieList
        )
    }

}
