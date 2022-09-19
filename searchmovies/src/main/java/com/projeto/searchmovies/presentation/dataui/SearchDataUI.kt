package com.projeto.searchmovies.presentation.dataui

data class SearchDataUI(
    val response: Boolean,
    val search: List<MovieItemDataUI>?,
    val error: String?
)