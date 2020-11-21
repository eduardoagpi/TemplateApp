package com.example.templateapp.account.login

import android.content.res.Resources
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.LoginUseCase
import com.example.templateapp.R
import com.example.templateapp.base.BaseViewModel
import kotlinx.coroutines.*

class LoginViewModel @ViewModelInject constructor(
    private val resources: Resources,
    private val loginUseCase: LoginUseCase
): BaseViewModel<LoginState, LoginActions>() {

    private var loading = false
    private var user = ""
    private var password = ""

    fun viewReady() {
        postFreshState()
    }

    fun onUserTextChanged(text: String) {
        user = text
        postFreshState()
    }

    fun onPasswordTextChanged(text: String) {
        password = text
        postFreshState()
    }

    fun doLogin() {
        loading = true
        postFreshState()
        viewModelScope.launch(Dispatchers.IO) {
            delay(2000)
            withContext(Dispatchers.Main) {
                loading = false
                postFreshState()
                when {
                    user.length<8 -> postAction(LoginActions.ShowToastErrorMessage(resources.getString(R.string.user_short)))
                    password.length<8 -> postAction(LoginActions.ShowToastErrorMessage(resources.getString(R.string.password_short)))
                    else -> postAction(LoginActions.NavigateToHome)
                }
            }
        }
    }

    override fun determineState(): LoginState {
        val enabled = user.isNotBlank() && password.isNotBlank() && !loading
        val text = if(enabled) "Continuar" else "Faltan campos"
        return LoginState(loading, LoginButtonState(text, enabled))
    }

}
