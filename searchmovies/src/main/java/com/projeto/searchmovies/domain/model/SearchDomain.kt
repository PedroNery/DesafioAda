package com.projeto.searchmovies.domain.model

data class SearchDomain(
    val response: Boolean,
    val search: List<MovieDomain>?,
    val totalResults: String?,
    val error: String?
)