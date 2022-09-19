package com.projeto.common.koin.aware.scopemodule

import androidx.annotation.LayoutRes
import com.projeto.common.base.extensions.scopedModules
import com.projeto.common.koin.aware.KoinAware
import com.projeto.common.koin.aware.getUniqueScopedQualifier
import org.koin.androidx.scope.ScopeActivity
import org.koin.core.scope.Scope
import org.koin.androidx.scope.getScopeId

abstract class KoinActivity(@LayoutRes layoutId: Int = 0, scopeModule: ScopeModule) :
    ScopeActivity(layoutId), KoinAware {

    override val subModules by scopedModules(scopeModule)
    override val scope: Scope by lazy {
        getKoin().createScope(getScopeId(), this.getUniqueScopedQualifier(), this)
    }

}
