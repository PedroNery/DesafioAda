package com.projeto.test.unit

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

@OptIn(ExperimentalCoroutinesApi::class)
class CoroutineTestRule : TestRule {
    private val testDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()
    override fun apply(base: Statement, description: Description): Statement =
        object : Statement(){
            override fun evaluate() {
                Dispatchers.setMain(testDispatcher)
                base.evaluate()
                Dispatchers.resetMain()
                testDispatcher.cleanupTestCoroutines()
            }
        }
}