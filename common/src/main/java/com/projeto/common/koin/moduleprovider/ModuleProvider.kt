package com.projeto.common.koin.moduleprovider

import android.content.Context
import androidx.startup.Initializer
import org.koin.core.module.Module

abstract class ModuleProvider : Initializer<List<Module>>, KoinModule {

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()

    override fun create(context: Context): List<Module> {
        ModuleInitializer.add(modules)
        return modules
    }

}