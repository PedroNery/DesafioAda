package com.projeto.common.base.extensions

import com.projeto.common.koin.aware.KoinAware
import com.projeto.common.koin.aware.ModuleList
import com.projeto.common.koin.aware.getUniqueScopedQualifier
import com.projeto.common.koin.aware.scopemodule.ScopeModule
import org.koin.core.module.Module

fun KoinAware.modules(
    vararg modules: Module,
    keepAfterDestroy: Boolean = true
) = subModules(modules.asList(), keepAfterDestroy)

private fun KoinAware.subModules(
    modules: List<Module>,
    keepAfterDestroy: Boolean
) = lazy {
    ModuleList(modules, keepAfterDestroy)
}

fun KoinAware.scopedModules(
    scopeModule: ScopeModule
) = subModules(scopeModule.getScopedModules(this.getUniqueScopedQualifier()), false)