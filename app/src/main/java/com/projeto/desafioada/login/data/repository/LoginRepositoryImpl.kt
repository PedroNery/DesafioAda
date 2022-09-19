package com.projeto.desafioada.login.data.repository

import com.google.firebase.auth.AuthResult
import com.projeto.desafioada.login.data.datasource.FirebaseAuthLocalDataSource
import kotlinx.coroutines.flow.Flow

class LoginRepositoryImpl(
    private val firebaseAuthLocalDataSource: FirebaseAuthLocalDataSource
): LoginRepository {

    override fun signInWithEmailAndPassword(email: String, password: String): Flow<AuthResult> =
        firebaseAuthLocalDataSource.signInWithEmailAndPassword(email, password)

    override fun createUserWithEmailAndPassword(email: String, password: String): Flow<AuthResult> =
        firebaseAuthLocalDataSource.createUserWithEmailAndPassword(email, password)

    override fun isUserLogged(): Flow<Boolean> =
        firebaseAuthLocalDataSource.isUserLogged()

    override fun signOut() {
        firebaseAuthLocalDataSource.signOut()
    }

}