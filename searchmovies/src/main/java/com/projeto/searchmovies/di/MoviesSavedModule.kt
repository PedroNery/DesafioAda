package com.projeto.searchmovies.di

import androidx.room.Room
import com.projeto.common.koin.aware.scopemodule.ScopeModule
import com.projeto.searchmovies.data.datasource.MovieRemoteDataSourceImpl
import com.projeto.searchmovies.data.datasource.MoviesLocalDataSource
import com.projeto.searchmovies.data.datasource.MoviesLocalDataSourceImpl
import com.projeto.searchmovies.data.datasource.MoviesRemoteDataSource
import com.projeto.searchmovies.data.local.MovieDatabase
import com.projeto.searchmovies.data.repository.MoviesRepository
import com.projeto.searchmovies.data.repository.MoviesRepositoryImpl
import com.projeto.searchmovies.data.service.MovieService
import com.projeto.searchmovies.domain.usecase.SavedMoviesUseCase
import com.projeto.searchmovies.domain.usecase.SavedMoviesUseCaseImpl
import com.projeto.searchmovies.network.getMovieRetrofit
import com.projeto.searchmovies.presentation.viewmodel.SavedMoviesViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.ScopeDSL
import retrofit2.Retrofit

internal object MoviesSavedModule : ScopeModule() {
    override fun ScopeDSL.getData() = dependencies {
        factory {
            Room.databaseBuilder(
                androidApplication(),
                MovieDatabase::class.java,
                "movies.db"
            )
                .fallbackToDestructiveMigration()
                .build()
        }

        factory { get<MovieDatabase>().dao }

        factory<MoviesRemoteDataSource> {
            MovieRemoteDataSourceImpl(getService(getMovieRetrofit()))
        }

        factory<MoviesLocalDataSource> {
            MoviesLocalDataSourceImpl(get())
        }

        factory<MoviesRepository> {
            MoviesRepositoryImpl(get(), get())
        }
    }

    override fun ScopeDSL.getDomain() = dependencies {
        factory<SavedMoviesUseCase> {
            SavedMoviesUseCaseImpl(get())
        }
    }

    override fun ScopeDSL.getPresentation() = dependencies {
        viewModel {
            SavedMoviesViewModel(get())
        }
    }
}

private fun getService(retrofit: Retrofit) = retrofit.create(MovieService::class.java)