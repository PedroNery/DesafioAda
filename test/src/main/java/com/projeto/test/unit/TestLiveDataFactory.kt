package com.projeto.test.unit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.projeto.common.base.factory.LiveDataFactory
import com.projeto.common.base.intent.UIAction
import com.projeto.common.base.intent.UIState
import com.projeto.common.base.livedata.OneShotLiveData

@Suppress("UNCHECKED_CAST")
internal class TestLiveDataFactory(
    val stateObserver: Observer<UIState>,
    val actionObserver: Observer<UIAction>
) : LiveDataFactory {

    override fun <T : UIState> createMutableLiveData(initialState: T): MutableLiveData<T> {
        return MutableLiveData(initialState).apply {
            observeForever(stateObserver as Observer<T>)
        }
    }

    override fun <T : UIAction> createOneShotLiveData(): OneShotLiveData<T> {
        return OneShotLiveData<T>().apply {
            observeForever(actionObserver as Observer<T>)
        }
    }

}