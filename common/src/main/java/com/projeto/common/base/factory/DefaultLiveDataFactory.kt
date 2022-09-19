package com.projeto.common.base.factory

import androidx.lifecycle.MutableLiveData
import com.projeto.common.base.intent.UIAction
import com.projeto.common.base.intent.UIState
import com.projeto.common.base.livedata.OneShotLiveData

class DefaultLiveDataFactory : LiveDataFactory {

    override fun <T : UIState> createMutableLiveData(initialState: T): MutableLiveData<T> {
        return MutableLiveData(initialState)
    }

    override fun <T : UIAction> createOneShotLiveData() = OneShotLiveData<T>()

}