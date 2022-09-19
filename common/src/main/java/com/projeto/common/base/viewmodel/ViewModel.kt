package com.projeto.common.base.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.projeto.common.base.intent.UIAction
import com.projeto.common.base.intent.UIState

abstract class ViewModel<State : UIState, Action : UIAction>(
    initialState : State
) : ViewModel() {

    private val viewModelState = com.projeto.common.base.intent.State(initialState)
    private val viewModelAction = com.projeto.common.base.intent.Action<Action>()

    val state: LiveData<State> = viewModelState.state
    val action: LiveData<Action> = viewModelAction.action

    protected fun setState(state: (State) -> State) {
        viewModelState.setState(state)
    }

    protected fun sendAction(action: () -> Action) {
        viewModelAction.sendAction(action)
    }

}