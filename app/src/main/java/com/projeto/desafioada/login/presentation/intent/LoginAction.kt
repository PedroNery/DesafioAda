package com.projeto.desafioada.login.presentation.intent

import com.projeto.common.base.intent.UIAction

sealed class LoginAction : UIAction {
    object NavigateToChooseFeature: LoginAction()
}