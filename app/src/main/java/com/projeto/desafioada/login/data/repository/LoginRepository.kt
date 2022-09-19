package com.projeto.desafioada.login.data.repository

import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    fun signInWithEmailAndPassword(email: String, password: String): Flow<AuthResult>
    fun createUserWithEmailAndPassword(email: String, password: String): Flow<AuthResult>
    fun isUserLogged(): Flow<Boolean>
    fun signOut()
}