package com.projeto.searchmovies.data.datasource

import com.projeto.searchmovies.data.local.MovieEntity
import kotlinx.coroutines.flow.Flow

interface MoviesLocalDataSource {
    suspend fun saveMovie(movieEntity: MovieEntity)
    suspend fun deleteMovie(id: String)
    fun getAllMovies(): Flow<List<MovieEntity>>
}