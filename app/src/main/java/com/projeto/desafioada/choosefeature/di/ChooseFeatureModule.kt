package com.projeto.desafioada.choosefeature.di

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.projeto.common.koin.aware.scopemodule.ScopeModule
import com.projeto.desafioada.choosefeature.domain.ChooseFeatureUseCase
import com.projeto.desafioada.choosefeature.domain.ChooseFeatureUseCaseImpl
import com.projeto.desafioada.choosefeature.presentation.viewmodel.ChooseFeatureViewModel
import com.projeto.desafioada.login.data.datasource.FirebaseAuthLocalDataSource
import com.projeto.desafioada.login.data.datasource.FirebaseAuthLocalDataSourceImpl
import com.projeto.desafioada.login.data.repository.LoginRepository
import com.projeto.desafioada.login.data.repository.LoginRepositoryImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.ScopeDSL

object ChooseFeatureModule : ScopeModule() {

    override fun ScopeDSL.getData() = dependencies {
        factory<FirebaseAuthLocalDataSource> {
            FirebaseAuthLocalDataSourceImpl(Firebase.auth)
        }
        factory<LoginRepository> {
            LoginRepositoryImpl(get())
        }
    }

    override fun ScopeDSL.getDomain() = dependencies {
        factory<ChooseFeatureUseCase> {
            ChooseFeatureUseCaseImpl(get())
        }
    }
    override fun ScopeDSL.getPresentation() = dependencies {
        viewModel {
            ChooseFeatureViewModel(get())
        }
    }
}