package com.projeto.searchmovies.data.local

import androidx.room.*

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovie(movieEntity: MovieEntity)

    @Query("SELECT * FROM movieEntity")
    suspend fun getMovies(): List<MovieEntity>

    @Query("DELETE FROM movieEntity WHERE imdbId = :id_")
    suspend fun deleteMovie(id_: String)

}