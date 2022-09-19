package com.projeto.desafioada.login.domain

import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow

interface LoginUseCase {
    fun signInWithEmailAndPassword(email: String, password: String): Flow<AuthResult>
    fun createUserWithEmailAndPassword(email: String, password: String): Flow<AuthResult>
    fun isUserLogged(): Flow<Boolean>
}