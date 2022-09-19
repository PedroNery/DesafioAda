package com.projeto.common.koin

import android.app.Application
import com.projeto.common.koin.moduleprovider.ModuleInitializer
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level
import org.koin.dsl.KoinAppDeclaration

object KoinAppDeclarationProvider {

    fun get(application: Application) : KoinAppDeclaration = {
        androidLogger(Level.ERROR)
        androidContext(application)
        modules(ModuleInitializer.modules.toList())
    }

}