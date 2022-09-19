package com.projeto.desafioada.choosefeature.presentation.activity

import android.content.Intent
import android.os.Bundle
import com.projeto.common.base.extensions.onAction
import com.projeto.common.base.viewbinding.viewBinding
import com.projeto.common.koin.aware.scopemodule.KoinActivity
import com.projeto.desafioada.R
import com.projeto.desafioada.choosefeature.di.ChooseFeatureModule
import com.projeto.desafioada.choosefeature.presentation.viewmodel.ChooseFeatureViewModel
import com.projeto.desafioada.choosefeature.presentation.intent.ChooseFeatureAction
import com.projeto.desafioada.databinding.ActivityChooseFeatureBinding
import com.projeto.desafioada.login.presentation.activity.LoginActivity
import com.projeto.navigation.MovieListNavigation
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChooseFeatureActivity : KoinActivity(R.layout.activity_choose_feature, ChooseFeatureModule) {

    private val viewModel: ChooseFeatureViewModel by viewModel()
    private val binding: ActivityChooseFeatureBinding by viewBinding(R.id.activityChooseFeatureRoot)
    private val movieListNavigation: MovieListNavigation by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onAction(viewModel, ::handleAction)
        setupCards()
        setupSignOut()
    }

    private fun setupCards() {
        binding.musicCard.bind(
            "Música",
            "Veja suas músicas disponíveis",
            com.projeto.designsystem.R.drawable.ic_music
        )
        binding.movieCard.setOnClickListener {
            viewModel.navigateToMovieList()
        }
        binding.movieCard.bind(
            "Filmes",
            "Veja seus fílmes disponíveis",
            com.projeto.designsystem.R.drawable.ic_movie
        )
    }

    private fun setupSignOut() {
        binding.btSignout.setOnClickListener {
            viewModel.signOut()
        }
    }

    private fun handleAction(action: ChooseFeatureAction) {
        when (action) {
            ChooseFeatureAction.NavigateToMovieList -> {
                movieListNavigation.navigateToMovieList(this)
            }
            ChooseFeatureAction.NavigateToMusicList -> {
                // Implementar
            }
            ChooseFeatureAction.NavigateToLogin -> {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }
    }
}