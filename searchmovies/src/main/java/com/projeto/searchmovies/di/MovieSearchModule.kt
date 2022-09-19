package com.projeto.searchmovies.di

import com.projeto.common.koin.aware.scopemodule.ScopeModule
import com.projeto.searchmovies.data.datasource.MovieRemoteDataSourceImpl
import com.projeto.searchmovies.data.datasource.MoviesLocalDataSource
import com.projeto.searchmovies.data.datasource.MoviesLocalDataSourceImpl
import com.projeto.searchmovies.data.datasource.MoviesRemoteDataSource
import com.projeto.searchmovies.data.local.getMovieDao
import com.projeto.searchmovies.data.repository.MoviesRepository
import com.projeto.searchmovies.data.repository.MoviesRepositoryImpl
import com.projeto.searchmovies.data.service.MovieService
import com.projeto.searchmovies.domain.usecase.SearchMoviesUseCase
import com.projeto.searchmovies.domain.usecase.SearchMoviesUseCaseImpl
import com.projeto.searchmovies.network.getMovieRetrofit
import com.projeto.searchmovies.presentation.viewmodel.SearchMoviesViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.ScopeDSL
import retrofit2.Retrofit

internal object MovieSearchModule : ScopeModule() {
    override fun ScopeDSL.getData() = dependencies {
        factory<MoviesRemoteDataSource> {
            MovieRemoteDataSourceImpl(getService(getMovieRetrofit()))
        }
        factory<MoviesLocalDataSource> {
            MoviesLocalDataSourceImpl(getMovieDao(androidContext()))
        }
        factory<MoviesRepository> {
            MoviesRepositoryImpl(get(), get())
        }
    }

    override fun ScopeDSL.getDomain() = dependencies {
        factory<SearchMoviesUseCase> {
            SearchMoviesUseCaseImpl(get())
        }
    }

    override fun ScopeDSL.getPresentation() = dependencies {
        viewModel {
            SearchMoviesViewModel(get())
        }
    }
}

private fun getService(retrofit: Retrofit) = retrofit.create(MovieService::class.java)