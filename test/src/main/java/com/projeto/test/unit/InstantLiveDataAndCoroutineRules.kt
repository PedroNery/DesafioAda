package com.projeto.test.unit

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.rules.RuleChain
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class InstantLiveDataAndCoroutineRules : TestRule {
    override fun apply(base: Statement, description: Description): Statement {
        return RuleChain
            .outerRule(InstantTaskExecutorRule())
            .around(CoroutineTestRule())
            .apply(base, description)
    }
}