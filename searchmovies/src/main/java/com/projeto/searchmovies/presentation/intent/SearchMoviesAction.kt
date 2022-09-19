package com.projeto.searchmovies.presentation.intent

import com.projeto.common.base.intent.UIAction
import com.projeto.searchmovies.presentation.dataui.MovieItemDataUI

sealed class SearchMoviesAction : UIAction {
    data class SaveMovieLocal(val movie: MovieItemDataUI) : SearchMoviesAction()
    object ReturnToSavedMovies: SearchMoviesAction()
}