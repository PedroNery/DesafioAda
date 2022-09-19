package com.projeto.common.koin.aware

import org.koin.core.qualifier.Qualifier
import org.koin.core.qualifier.StringQualifier
import org.koin.ext.getFullName

fun Any.getUniqueScopedQualifier(): Qualifier =
    StringQualifier(this::class.getFullName() + '@' + System.identityHashCode(this))