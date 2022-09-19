package com.projeto.searchmovies.data.local

import android.content.Context
import androidx.room.Room

fun getMovieDao(context: Context): MovieDao {
    return Room.databaseBuilder(
        context,
        MovieDatabase::class.java,
        "movies.db"
    ).build().dao
}