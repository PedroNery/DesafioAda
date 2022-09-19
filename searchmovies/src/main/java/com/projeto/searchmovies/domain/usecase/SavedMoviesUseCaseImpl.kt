package com.projeto.searchmovies.domain.usecase

import com.projeto.searchmovies.data.repository.MoviesRepository
import com.projeto.searchmovies.domain.model.MovieDomain
import kotlinx.coroutines.flow.Flow

class SavedMoviesUseCaseImpl(
    private val moviesRepository: MoviesRepository
) : SavedMoviesUseCase {
    override fun getSavedMovies(): Flow<List<MovieDomain>> {
        return moviesRepository.getSavedMovies()
    }

    override suspend fun deleteMovie(id: String) {
        moviesRepository.deleteMovie(id)
    }
}