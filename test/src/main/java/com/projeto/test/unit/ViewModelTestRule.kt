package com.projeto.test.unit

import androidx.lifecycle.Observer
import com.projeto.common.base.factory.DefaultLiveDataFactory
import com.projeto.common.base.intent.UIAction
import com.projeto.common.base.intent.UIState
import com.projeto.common.base.viewmodel.ViewModelPlugins
import io.mockk.mockk
import org.junit.rules.ExternalResource

@Suppress("UNCHECKED_CAST", "VisibleForTests")
class ViewModelTestRule(
    stateObserver: Observer<UIState> = mockk(),
    actionObserver: Observer<UIAction> = mockk()
) : ExternalResource() {

    private val factory = TestLiveDataFactory(stateObserver, actionObserver)

    fun <T : UIState> getStateObsever(): Observer<T> {
        try {
            return factory.stateObserver as Observer<T>
        } catch (e: UninitializedPropertyAccessException) {
            val message = "getStateObserver() should be called after ViewModel initialization"
            throw ViewModelTestRuleException(message, e)
        }
    }

    fun <T : UIAction> getActionObsever(): Observer<T> {
        try {
            return factory.actionObserver as Observer<T>
        } catch (e: UninitializedPropertyAccessException) {
            val message = "getActionObserver() should be called after ViewModel initialization"
            throw ViewModelTestRuleException(message, e)
        }
    }

    override fun before() {
        ViewModelPlugins.setFactory(factory)
    }

    override fun after() {
        ViewModelPlugins.setFactory(DefaultLiveDataFactory())
    }

}