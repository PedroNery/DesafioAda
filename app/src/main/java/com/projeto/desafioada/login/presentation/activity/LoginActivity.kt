package com.projeto.desafioada.login.presentation.activity

import android.content.Intent
import android.os.Bundle
import androidx.core.view.isVisible
import com.projeto.common.base.extensions.onAction
import com.projeto.common.base.extensions.onStateChange
import com.projeto.common.base.viewbinding.viewBinding
import com.projeto.common.koin.aware.scopemodule.KoinActivity
import com.projeto.desafioada.R
import com.projeto.desafioada.choosefeature.presentation.activity.ChooseFeatureActivity
import com.projeto.desafioada.databinding.ActivityLoginBinding
import com.projeto.desafioada.login.di.LoginModule
import com.projeto.desafioada.login.presentation.intent.LoginAction
import com.projeto.desafioada.login.presentation.state.LoginState
import com.projeto.desafioada.login.presentation.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : KoinActivity(R.layout.activity_login, LoginModule) {

    private val viewModel: LoginViewModel by viewModel()
    private val binding: ActivityLoginBinding by viewBinding(R.id.activityLoginRoot)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupButtons()
        onStateChange(viewModel, ::handleState)
        onAction(viewModel, :: handleAction)
    }

    override fun onStart() {
        super.onStart()
        viewModel.isUserLogged()
    }

    private fun setupButtons() {
        binding.btLogin.setOnClickListener {
            viewModel.signInWithEmailAndPassword(
                binding.etUsername.text,
                binding.etPassword.text
            )
        }
        binding.btRegistration.setOnClickListener {
            viewModel.createUserWithEmailAndPassword(
                binding.etUsername.text,
                binding.etPassword.text
            )
        }
    }

    private fun handleState(state: LoginState) {
        binding.loading.isVisible = state.showLoading
        binding.tvError.isVisible = state.showError
        binding.tvError.text = state.errorMessage
    }

    private fun handleAction(action: LoginAction) {
        when (action) {
            is LoginAction.NavigateToChooseFeature -> {
                startActivity(Intent(this, ChooseFeatureActivity::class.java))
                finish()
            }
        }
    }

}