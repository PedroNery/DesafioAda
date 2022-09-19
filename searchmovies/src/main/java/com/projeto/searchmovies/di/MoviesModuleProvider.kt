package com.projeto.searchmovies.di

import com.projeto.common.koin.moduleprovider.ModuleProvider
import com.projeto.navigation.MovieListNavigation
import com.projeto.searchmovies.navigation.MovieListNavigationImpl
import org.koin.core.module.Module
import org.koin.dsl.module

class MoviesModuleProvider : ModuleProvider() {
    override val modules: List<Module>
        get() = listOf(
            navigationModule
        )

    private val navigationModule = module {
        factory<MovieListNavigation> {
            MovieListNavigationImpl()
        }
    }
}