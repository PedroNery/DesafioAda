package com.projeto.searchmusics.presentation.intent

import com.projeto.common.base.intent.UIAction

sealed class SavedMusicsAction : UIAction {
    object NavigateToSearch: SavedMusicsAction()
    object ReloadList: SavedMusicsAction()
}