package com.projeto.searchmovies.data.datasource

import com.projeto.searchmovies.data.model.SearchResponse
import com.projeto.searchmovies.data.service.MovieService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRemoteDataSourceImpl(
    private val movieListService: MovieService
) : MoviesRemoteDataSource {

    override fun getMoviesByName(name: String) : Flow<SearchResponse> =
        flow {
            emit(movieListService.getMoviesByName(name))
        }

}