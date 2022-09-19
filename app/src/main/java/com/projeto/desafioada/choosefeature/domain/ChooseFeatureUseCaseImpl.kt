package com.projeto.desafioada.choosefeature.domain

import com.projeto.desafioada.choosefeature.domain.ChooseFeatureUseCase
import com.projeto.desafioada.login.data.repository.LoginRepository

class ChooseFeatureUseCaseImpl(
    private val loginRepository: LoginRepository
): ChooseFeatureUseCase {
    override fun signOut() {
        loginRepository.signOut()
    }
}