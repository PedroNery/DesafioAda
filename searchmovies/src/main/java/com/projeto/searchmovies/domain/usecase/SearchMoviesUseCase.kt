package com.projeto.searchmovies.domain.usecase

import com.projeto.searchmovies.domain.model.MovieDomain
import com.projeto.searchmovies.domain.model.SearchDomain
import com.projeto.searchmovies.presentation.dataui.SearchDataUI
import kotlinx.coroutines.flow.Flow

interface SearchMoviesUseCase {
    fun getMoviesByName(name: String) : Flow<SearchDataUI>
    suspend fun saveMovies(movieDomain: MovieDomain)
}