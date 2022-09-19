package com.projeto.searchmusics.domain.usecase

import kotlinx.coroutines.flow.Flow

interface SavedMusicsUseCase {
    fun getSavedMovies(): Flow<List<String>>
    suspend fun deleteMovie(id: String)
}