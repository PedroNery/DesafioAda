package com.projeto.searchmovies.domain.usecase

import com.projeto.searchmovies.data.mapper.toDataUi
import com.projeto.searchmovies.data.repository.MoviesRepository
import com.projeto.searchmovies.domain.model.MovieDomain
import com.projeto.searchmovies.domain.model.SearchDomain
import com.projeto.searchmovies.presentation.dataui.SearchDataUI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class SearchMoviesUseCaseImpl (
    private val movieListRepository: MoviesRepository
) : SearchMoviesUseCase {

    override fun getMoviesByName(name: String) : Flow<SearchDataUI> {
        return if(name.length >= 3)
            movieListRepository.getMoviesByName(name).map {
                it.toDataUi()
            }
        else
            flow { throw IllegalArgumentException("Digite ao menos 3 letras") }
    }

    override suspend fun saveMovies(movieDomain: MovieDomain) {
        movieListRepository.saveMovie(movieDomain)
    }

}