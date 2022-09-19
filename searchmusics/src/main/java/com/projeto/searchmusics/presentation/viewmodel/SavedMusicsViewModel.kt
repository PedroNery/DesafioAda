package com.projeto.searchmusics.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.projeto.common.base.viewmodel.ViewModel
import com.projeto.searchmusics.domain.usecase.SavedMusicsUseCase
import com.projeto.searchmusics.presentation.intent.SavedMusicsAction
import com.projeto.searchmusics.presentation.state.SavedMovieState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class SavedMusicsViewModel(
    private val savedMoviesUseCase: SavedMusicsUseCase
) : ViewModel<SavedMovieState, SavedMusicsAction>(SavedMovieState()) {

    fun getAllMovies() {
        viewModelScope.launch {
            savedMoviesUseCase.getSavedMovies()
                .onStart { setState { state -> state.copy(showLoading = true) } }
                .collect { movieList ->
                    setState { state -> state.showContent(listOf()) }
                }
        }
    }

    fun deleteMovie(id: String) {
        viewModelScope.launch {
            savedMoviesUseCase.deleteMovie(id)
            sendAction { SavedMusicsAction.ReloadList }
        }
    }

    fun setNavigateToSearchAction() {
        sendAction { SavedMusicsAction.NavigateToSearch }
    }

}