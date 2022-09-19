package com.projeto.desafioada.login.data.datasource

import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow

interface FirebaseAuthLocalDataSource {
    fun signInWithEmailAndPassword(email: String, password: String): Flow<AuthResult>
    fun createUserWithEmailAndPassword(email: String, password: String): Flow<AuthResult>
    fun isUserLogged(): Flow<Boolean>
    fun signOut()
}