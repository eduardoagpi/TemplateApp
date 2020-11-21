package com.example.templateapp.account.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.widget.doOnTextChanged
import com.example.templateapp.R
import com.example.templateapp.base.BaseViewModelActivity
import com.example.templateapp.base.extensions.showOrHide
import com.example.templateapp.home.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : BaseViewModelActivity<LoginViewModel, LoginState, LoginActions>() {

    override val viewModel by viewModels<LoginViewModel>()
    lateinit var inputUser: TextView
    lateinit var inputPassword: TextView
    lateinit var loadingView: ProgressBar
    lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        viewModel.viewReady()

        inputUser = findViewById(R.id.inputUser)
        inputPassword = findViewById(R.id.inputPassword)
        loadingView = findViewById(R.id.progress_bar)
        loginButton = findViewById(R.id.btnLogin)

        inputPassword.doOnTextChanged { text, start, before, count -> viewModel.onUserTextChanged(text.toString()) }
        inputUser.doOnTextChanged { text, start, before, count -> viewModel.onPasswordTextChanged(text.toString()) }
        loginButton.setOnClickListener { viewModel.doLogin() }
    }

    override fun renderFreshState(freshState: LoginState) = freshState.run {
        loadingView.showOrHide(loading)
        loginButton.isEnabled = buttonState.enabled
    }

    override fun handleAction(action: LoginActions) {
        when (action) {
            is LoginActions.ShowToastErrorMessage -> Toast.makeText(this, action.errorMessage, Toast.LENGTH_SHORT).show()
            is LoginActions.NavigateToHome -> navigateToHome()
        }
    }

    private fun navigateToHome() {
        val intent = Intent(this, MainActivity::class.java).apply {
            //putExtra(EXTRA_MESSAGE, message)
        }
        startActivity(intent)
        finish()
    }
}
