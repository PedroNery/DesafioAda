package com.projeto.searchmovies.data.model

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("Response")
    val response: Boolean,
    @SerializedName("Search")
    val search: List<MovieResumedResponse>?,
    @SerializedName("totalResults")
    val totalResults: String?,
    @SerializedName("Error")
    val error: String?
)