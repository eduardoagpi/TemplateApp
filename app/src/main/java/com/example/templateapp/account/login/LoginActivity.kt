package com.example.templateapp.account.login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.widget.doOnTextChanged
import com.example.templateapp.R
import com.example.templateapp.base.BaseViewModelActivity
import com.example.templateapp.base.extensions.longToast
import com.example.templateapp.base.extensions.onTextChanged
import com.example.templateapp.base.extensions.showOrHide
import com.example.templateapp.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : BaseViewModelActivity<LoginViewModel, LoginState, LoginActions>() {

    override val viewModel by viewModels<LoginViewModel>()
    lateinit var inputUser: EditText
    lateinit var inputPassword: EditText
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

        inputPassword.onTextChanged { viewModel.onUserTextChanged(it) }
        inputPassword.onTextChanged { viewModel.onPasswordTextChanged(it) }
        loginButton.setOnClickListener { viewModel.doLogin() }
    }

    override fun renderFreshState(freshState: LoginState) = freshState.run {
        loadingView.showOrHide(loading)
        loginButton.isEnabled = buttonState.enabled
    }

    override fun handleAction(action: LoginActions) {
        when (action) {
            is LoginActions.ShowToastErrorMessage -> longToast(action.errorMessage)
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
