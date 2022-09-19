package com.projeto.desafioada.login.di

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.projeto.common.koin.aware.scopemodule.ScopeModule
import com.projeto.desafioada.login.data.datasource.FirebaseAuthLocalDataSource
import com.projeto.desafioada.login.data.datasource.FirebaseAuthLocalDataSourceImpl
import com.projeto.desafioada.login.data.repository.LoginRepository
import com.projeto.desafioada.login.data.repository.LoginRepositoryImpl
import com.projeto.desafioada.login.domain.LoginUseCase
import com.projeto.desafioada.login.domain.LoginUseCaseImpl
import com.projeto.desafioada.login.presentation.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.ScopeDSL

internal object LoginModule : ScopeModule() {
    override fun ScopeDSL.getData() = dependencies {
        factory<FirebaseAuthLocalDataSource> {
            FirebaseAuthLocalDataSourceImpl(Firebase.auth)
        }
        factory<LoginRepository> {
            LoginRepositoryImpl(get())
        }
    }

    override fun ScopeDSL.getDomain() = dependencies {
        factory<LoginUseCase> {
            LoginUseCaseImpl(get())
        }
    }

    override fun ScopeDSL.getPresentation() = dependencies {
        viewModel {
            LoginViewModel(get())
        }
    }
}