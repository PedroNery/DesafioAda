package com.projeto.searchmovies.presentation.intent

import com.projeto.common.base.intent.UIAction

sealed class SavedMoviesAction : UIAction {
    object NavigateToSearch: SavedMoviesAction()
    object ReloadList: SavedMoviesAction()
}