package com.projeto.searchmovies.data.datasource

import com.projeto.searchmovies.data.model.SearchResponse
import kotlinx.coroutines.flow.Flow

interface MoviesRemoteDataSource {
    fun getMoviesByName(name: String) : Flow<SearchResponse>
}