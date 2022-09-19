package com.projeto.searchmovies.domain.usecase

import com.projeto.searchmovies.domain.model.MovieDomain
import kotlinx.coroutines.flow.Flow

interface SavedMoviesUseCase {
    fun getSavedMovies(): Flow<List<MovieDomain>>
    suspend fun deleteMovie(id: String)
}