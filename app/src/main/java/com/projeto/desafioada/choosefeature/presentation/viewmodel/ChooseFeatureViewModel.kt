package com.projeto.desafioada.choosefeature.presentation.viewmodel

import com.projeto.common.base.viewmodel.ActionViewModel
import com.projeto.desafioada.choosefeature.domain.ChooseFeatureUseCase
import com.projeto.desafioada.choosefeature.presentation.intent.ChooseFeatureAction

class ChooseFeatureViewModel(
    private val chooseFeatureUseCase: ChooseFeatureUseCase
) : ActionViewModel<ChooseFeatureAction>() {

    fun navigateToMovieList() {
        setAction { ChooseFeatureAction.NavigateToMovieList }
    }

    fun navigateToMusicList() {
        setAction { ChooseFeatureAction.NavigateToMusicList }
    }

    fun signOut() {
        chooseFeatureUseCase.signOut()
        setAction { ChooseFeatureAction.NavigateToLogin }
    }
}