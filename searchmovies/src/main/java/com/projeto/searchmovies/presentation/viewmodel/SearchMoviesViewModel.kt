package com.projeto.searchmovies.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.projeto.common.base.viewmodel.ViewModel
import com.projeto.searchmovies.data.mapper.toDataUi
import com.projeto.searchmovies.data.mapper.toDomain
import com.projeto.searchmovies.domain.usecase.SearchMoviesUseCase
import com.projeto.searchmovies.presentation.dataui.MovieItemDataUI
import com.projeto.searchmovies.presentation.dataui.SearchDataUI
import com.projeto.searchmovies.presentation.intent.SearchMoviesAction
import com.projeto.searchmovies.presentation.state.SearchMovieState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class SearchMoviesViewModel(
    private val searchMoviesUseCase: SearchMoviesUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel<SearchMovieState, SearchMoviesAction>(SearchMovieState()) {

    fun searchMovieByName(name: String) {
        viewModelScope.launch {
            searchMoviesUseCase.getMoviesByName(name)
                .flowOn(dispatcher)
                .onStart { setState { state -> state.showLoading(true) } }
                .catch { handleError(it) }
                .collect { dataUi -> handleSearchResult(dataUi)
                }
        }
    }

    private fun handleError(error: Throwable) {
        if (error !is IllegalArgumentException)
            setState { state -> state.showLoading(false) }
        else
            setState { state -> state.showError(error.message) }
    }

    private fun handleSearchResult(searchDataUI: SearchDataUI) {
        if (searchDataUI.response)
            setState { state -> state.showContent(searchDataUI.search) }
        else
            setState { state -> state.showError(searchDataUI.error) }
    }

    fun saveMovie(movieItemDataUI: MovieItemDataUI) {
        viewModelScope.launch {
            searchMoviesUseCase.saveMovies(movieItemDataUI.toDomain())
            sendAction { SearchMoviesAction.ReturnToSavedMovies }
        }
    }

    fun setSaveMovieAction(movieItemDataUI: MovieItemDataUI) {
        sendAction { SearchMoviesAction.SaveMovieLocal(movieItemDataUI) }
    }

}