package com.projeto.desafioada.login.data.datasource

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class FirebaseAuthLocalDataSourceImpl(
    private val firebaseAuth: FirebaseAuth
) : FirebaseAuthLocalDataSource {

    override fun signInWithEmailAndPassword(email: String, password: String): Flow<AuthResult> = flow {
        val auth = firebaseAuth.signInWithEmailAndPassword(email, password).await()
        emit(auth)
    }

    override fun createUserWithEmailAndPassword(email: String, password: String): Flow<AuthResult> = flow {
        val auth = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
        emit(auth)
    }

    override fun isUserLogged(): Flow<Boolean> = flow {
        val currentUser = firebaseAuth.currentUser
        emit(currentUser != null)
    }

    override fun signOut() {
        firebaseAuth.signOut()
    }

}