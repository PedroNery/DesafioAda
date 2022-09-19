package com.projeto.searchmovies.data.mapper

import com.projeto.searchmovies.data.local.MovieEntity
import com.projeto.searchmovies.data.model.MovieResumedResponse
import com.projeto.searchmovies.data.model.SearchResponse
import com.projeto.searchmovies.domain.model.MovieDomain
import com.projeto.searchmovies.domain.model.SearchDomain
import com.projeto.searchmovies.presentation.dataui.MovieItemDataUI
import com.projeto.searchmovies.presentation.dataui.SearchDataUI

fun SearchResponse.toDomain(): SearchDomain {
    return SearchDomain(
        response = this.response,
        search = this.search?.toDomain(),
        totalResults = this.totalResults,
        error = this.error
    )
}

fun SearchDomain.toDataUi(): SearchDataUI {
    return SearchDataUI(
        response = this.response,
        search = this.search?.toDataUi(),
        error = this.error
    )
}

fun List<MovieDomain>.toDataUi() : List<MovieItemDataUI> {
    return this.map {
        MovieItemDataUI(
            type = 1,
            poster = it.poster,
            title = it.title,
            imdbID = it.imdbID
        )
    }
}

fun List<MovieResumedResponse>.toDomain(): List<MovieDomain> {
    return this.map {
        MovieDomain(
            title = it.title,
            imdbID = it.imdbID,
            poster = it.poster
        )
    }
}

fun List<MovieEntity>.entityToDomain(): List<MovieDomain> {
    return this.map {
        MovieDomain(
            imdbID = it.imdbId,
            title = it.title,
            poster = it.poster
        )
    }
}

fun MovieDomain.toEntity(): MovieEntity {
    return MovieEntity(
        imdbId = this.imdbID,
        title = this.title,
        poster = this.poster
    )
}

fun MovieItemDataUI.toDomain(): MovieDomain {
    return MovieDomain(
        imdbID = this.imdbID,
        title = this.title,
        poster = this.poster
    )
}