package com.projeto.common.koin.moduleprovider

import org.koin.core.module.Module

interface KoinModule {
    val modules: List<Module>
}