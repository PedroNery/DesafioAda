package com.projeto.common.base.viewmodel

import androidx.annotation.VisibleForTesting
import com.projeto.common.base.factory.DefaultLiveDataFactory
import com.projeto.common.base.factory.LiveDataFactory

object ViewModelPlugins {

    var factory: LiveDataFactory = DefaultLiveDataFactory()
        private set

    @VisibleForTesting
    fun setFactory(factory: LiveDataFactory){
        ViewModelPlugins.factory = factory
    }

}