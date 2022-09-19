package com.projeto.searchmovies.data.repository

import com.projeto.searchmovies.data.datasource.MoviesLocalDataSource
import com.projeto.searchmovies.data.datasource.MoviesRemoteDataSource
import com.projeto.searchmovies.data.mapper.entityToDomain
import com.projeto.searchmovies.data.mapper.toDomain
import com.projeto.searchmovies.data.mapper.toEntity
import com.projeto.searchmovies.domain.model.MovieDomain
import com.projeto.searchmovies.domain.model.SearchDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class MoviesRepositoryImpl(
    private val movieDataSourceRemote: MoviesRemoteDataSource,
    private val movieDataSourceLocal: MoviesLocalDataSource
) : MoviesRepository {
    override fun getMoviesByName(name: String): Flow<SearchDomain> {
        return movieDataSourceRemote.getMoviesByName(name).map {
            it.toDomain()
        }
    }

    override fun getSavedMovies(): Flow<List<MovieDomain>> {
        return movieDataSourceLocal.getAllMovies().map {
            it.entityToDomain()
        }
    }

    override suspend fun deleteMovie(id: String) {
        movieDataSourceLocal.deleteMovie(id)
    }

    override suspend fun saveMovie(movieDomain: MovieDomain) {
        movieDataSourceLocal.saveMovie(movieDomain.toEntity())
    }
}
