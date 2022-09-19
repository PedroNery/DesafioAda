package com.projeto.searchmusics.di

import androidx.room.Room
import com.projeto.common.koin.aware.scopemodule.ScopeModule
import com.projeto.searchmusics.presentation.viewmodel.SavedMusicsViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.ScopeDSL
import retrofit2.Retrofit

internal object MoviesSavedModule : ScopeModule() {
    override fun ScopeDSL.getData() = dependencies {

    }

    override fun ScopeDSL.getDomain() = dependencies {

    }

    override fun ScopeDSL.getPresentation() = dependencies {
        viewModel {
            SavedMusicsViewModel(get())
        }
    }
}
