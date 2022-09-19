package com.projeto.desafioada.choosefeature.presentation.intent

import com.projeto.common.base.intent.UIAction

sealed class ChooseFeatureAction : UIAction {
    object NavigateToMovieList : ChooseFeatureAction()
    object NavigateToMusicList : ChooseFeatureAction()
    object NavigateToLogin : ChooseFeatureAction()
}