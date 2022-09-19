package com.projeto.searchmovies.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieEntity(
    @PrimaryKey(autoGenerate = false)
    val imdbId: String,
    val title: String,
    val poster: String
)