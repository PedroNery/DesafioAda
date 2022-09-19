package com.projeto.searchmovies.data.model

import com.google.gson.annotations.SerializedName

data class MovieResumedResponse(
    @SerializedName("Poster")
    val poster: String,
    @SerializedName("Title")
    val title: String,
    @SerializedName("Type")
    val type: String,
    @SerializedName("Year")
    val year: String,
    @SerializedName("imdbID")
    val imdbID: String
)