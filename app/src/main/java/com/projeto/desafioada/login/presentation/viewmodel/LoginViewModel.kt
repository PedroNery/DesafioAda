package com.projeto.desafioada.login.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.projeto.common.base.viewmodel.ViewModel
import com.projeto.desafioada.login.domain.LoginUseCase
import com.projeto.desafioada.login.presentation.intent.LoginAction
import com.projeto.desafioada.login.presentation.state.LoginState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel<LoginState, LoginAction>(LoginState()) {

    fun signInWithEmailAndPassword(email: String, password: String) {
        viewModelScope.launch {
            loginUseCase.signInWithEmailAndPassword(email, password)
                .onStart { setState { state -> state.showLoading() } }
                .catch {
                    setState { state -> state.showError(it.message) }
                }
                .collect {
                    sendAction { LoginAction.NavigateToChooseFeature }
                }
        }
    }

    fun createUserWithEmailAndPassword(email: String, password: String) {
        viewModelScope.launch {
            loginUseCase.createUserWithEmailAndPassword(email, password)
                .onStart { setState { state -> state.showLoading() } }
                .catch {
                    setState { state -> state.showError(it.message) }
                }
                .collect {
                    sendAction { LoginAction.NavigateToChooseFeature }
                }
        }
    }

    fun isUserLogged() {
        viewModelScope.launch {
            loginUseCase.isUserLogged()
                .onStart { setState { state -> state.showLoading() } }
                .catch {
                    setState { state -> state.showError(it.message) }
                }
                .onCompletion {
                    setState { state -> state.showLoading(false) }
                }
                .collect { userLogged ->
                    if (userLogged) {
                        sendAction { LoginAction.NavigateToChooseFeature }
                    }
                }
        }
    }

}