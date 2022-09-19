package com.projeto.desafioada.login.domain

import com.google.firebase.auth.AuthResult
import com.projeto.desafioada.login.data.repository.LoginRepository
import kotlinx.coroutines.flow.Flow

class LoginUseCaseImpl(
    private val loginRepository: LoginRepository
): LoginUseCase {
    override fun signInWithEmailAndPassword(email: String, password: String): Flow<AuthResult> =
        loginRepository.signInWithEmailAndPassword(email, password)

    override fun createUserWithEmailAndPassword(email: String, password: String): Flow<AuthResult> =
        loginRepository.createUserWithEmailAndPassword(email, password)

    override fun isUserLogged(): Flow<Boolean> =
        loginRepository.isUserLogged()
}