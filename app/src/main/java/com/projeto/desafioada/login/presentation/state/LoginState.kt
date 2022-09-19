package com.projeto.desafioada.login.presentation.state

import com.projeto.common.base.intent.UIState

data class LoginState(
    val showLoading: Boolean = false,
    val showError: Boolean = false,
    val errorMessage: String = ""
) : UIState {

    fun showLoading(loading: Boolean = true): LoginState {
        return this.copy(
            showError = false,
            showLoading = loading,
            errorMessage = ""
        )
    }

    fun showError(message: String?): LoginState {
        return this.copy(
            showError = true,
            errorMessage = message ?: "",
            showLoading = false
        )
    }
}
