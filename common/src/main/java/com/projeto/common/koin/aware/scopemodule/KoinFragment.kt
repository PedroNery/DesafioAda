package com.projeto.common.koin.aware.scopemodule

import androidx.annotation.LayoutRes
import com.projeto.common.base.extensions.scopedModules
import com.projeto.common.koin.aware.KoinAware
import com.projeto.common.koin.aware.getUniqueScopedQualifier
import org.koin.androidx.scope.ScopeFragment
import org.koin.core.scope.Scope
import org.koin.core.scope.getScopeId

abstract class KoinFragment(@LayoutRes layoutId: Int = 0, scopeModule: ScopeModule = EmptyScopeModule()) :
    ScopeFragment(layoutId), KoinAware{

    override val subModules by scopedModules(scopeModule)
    override val scope: Scope by lazy {
        val scope = getKoin().createScope(getScopeId(), this.getUniqueScopedQualifier(), this)
        scopeActivity?.let { scope.linkTo(it.scope) }
        scope
    }

}

internal class EmptyScopeModule: ScopeModule()