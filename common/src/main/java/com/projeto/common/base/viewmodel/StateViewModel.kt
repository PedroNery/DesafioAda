package com.projeto.common.base.viewmodel

import androidx.lifecycle.ViewModel
import com.projeto.common.base.intent.State
import com.projeto.common.base.intent.UIState

abstract class StateViewModel<State : UIState>(initialState: State) : ViewModel() {

    private val stateAtribute = State(initialState)

    fun getState() = stateAtribute.state

    fun setState(state: (State) -> State) {
        stateAtribute.setState(state)
    }
}