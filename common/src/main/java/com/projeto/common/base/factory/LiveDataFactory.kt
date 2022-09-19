package com.projeto.common.base.factory

import androidx.lifecycle.MutableLiveData
import com.projeto.common.base.intent.UIAction
import com.projeto.common.base.intent.UIState
import com.projeto.common.base.livedata.OneShotLiveData

interface LiveDataFactory {

    fun<T : UIState> createMutableLiveData(initialState: T): MutableLiveData<T>

    fun<T : UIAction> createOneShotLiveData(): OneShotLiveData<T>

}