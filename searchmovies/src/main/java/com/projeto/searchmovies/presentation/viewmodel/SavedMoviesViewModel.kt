package com.projeto.searchmovies.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.projeto.common.base.viewmodel.ViewModel
import com.projeto.searchmovies.data.mapper.toDataUi
import com.projeto.searchmovies.domain.usecase.SavedMoviesUseCase
import com.projeto.searchmovies.presentation.intent.SavedMoviesAction
import com.projeto.searchmovies.presentation.state.SavedMovieState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class SavedMoviesViewModel(
    private val savedMoviesUseCase: SavedMoviesUseCase
) : ViewModel<SavedMovieState, SavedMoviesAction>(SavedMovieState()) {

    fun getAllMovies() {
        viewModelScope.launch {
            savedMoviesUseCase.getSavedMovies()
                .onStart { setState { state -> state.copy(showLoading = true) } }
                .collect { movieList ->
                    setState { state -> state.showContent(movieList.toDataUi()) }
                }
        }
    }

    fun deleteMovie(id: String) {
        viewModelScope.launch {
            savedMoviesUseCase.deleteMovie(id)
            sendAction { SavedMoviesAction.ReloadList }
        }
    }

    fun setNavigateToSearchAction() {
        sendAction { SavedMoviesAction.NavigateToSearch }
    }

}