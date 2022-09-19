package com.projeto.searchmovies.data.repository

import com.projeto.searchmovies.domain.model.MovieDomain
import com.projeto.searchmovies.domain.model.SearchDomain
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    fun getMoviesByName(name: String): Flow<SearchDomain>
    fun getSavedMovies(): Flow<List<MovieDomain>>
    suspend fun deleteMovie(id: String)
    suspend fun saveMovie(movieDomain: MovieDomain)
}