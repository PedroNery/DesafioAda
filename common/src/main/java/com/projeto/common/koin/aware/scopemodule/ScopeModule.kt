package com.projeto.common.koin.aware.scopemodule

import org.koin.core.definition.BeanDefinition
import org.koin.core.module.Module
import org.koin.core.qualifier.Qualifier
import org.koin.dsl.ScopeDSL
import org.koin.dsl.module

internal typealias BeanDefinitionList = List<BeanDefinition<*>>
internal typealias BeanDefinitionBlockHandler = MutableList<BeanDefinition<*>>.() -> Unit

abstract class ScopeModule {

    protected open fun ScopeDSL.getData(): BeanDefinitionList = emptyList()
    protected open fun ScopeDSL.getDomain(): BeanDefinitionList = emptyList()
    protected open fun ScopeDSL.getPresentation(): BeanDefinitionList = emptyList()
    protected open fun ScopeDSL.getAdditionalModule(): BeanDefinitionList = emptyList()

    protected fun dependencies(block: BeanDefinitionBlockHandler) =
        mutableListOf<BeanDefinition<*>>().apply(block)

    fun getScopedModules(qualifier: Qualifier): List<Module> {
        val scopedModule = module {
            scope(qualifier) {
                getData()
                getDomain()
                getPresentation()
                getAdditionalModule()
            }
        }
        return listOf(scopedModule)
    }
}