package com.projeto.searchmovies.data.datasource

import com.projeto.searchmovies.data.local.MovieDao
import com.projeto.searchmovies.data.local.MovieEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MoviesLocalDataSourceImpl(
    private val movieDao: MovieDao
): MoviesLocalDataSource {
    override suspend fun saveMovie(movieEntity: MovieEntity) {
        movieDao.saveMovie(movieEntity)
    }

    override suspend fun deleteMovie(id: String) {
        movieDao.deleteMovie(id)
    }

    override fun getAllMovies(): Flow<List<MovieEntity>> = flow {
        emit(movieDao.getMovies())
    }
}