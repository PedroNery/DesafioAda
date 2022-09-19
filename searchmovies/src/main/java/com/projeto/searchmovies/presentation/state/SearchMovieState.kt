package com.projeto.searchmovies.presentation.state

import com.projeto.common.base.intent.UIState
import com.projeto.searchmovies.presentation.dataui.MovieItemDataUI

data class SearchMovieState(
    val showLoading: Boolean = false,
    val showError: Boolean = false,
    val errorMessage: String = "",
    val showContent: Boolean = false,
    val movieList: List<MovieItemDataUI>? = listOf()
) : UIState {

    fun showLoading(loading: Boolean): SearchMovieState {
        return this.copy(
            showError = false,
            showContent = false,
            showLoading = loading,
            errorMessage = ""
        )
    }

    fun showError(message: String?): SearchMovieState {
        return this.copy(
            showError = true,
            errorMessage = message ?: "",
            showContent = false,
            showLoading = false
        )
    }

    fun showContent(movieList: List<MovieItemDataUI>?) : SearchMovieState {
        return this.copy(
            showError = false,
            showLoading = false,
            showContent = true,
            movieList = movieList,
            errorMessage = ""
        )
    }

}