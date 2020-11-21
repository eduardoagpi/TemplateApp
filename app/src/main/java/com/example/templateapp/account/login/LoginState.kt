package com.example.templateapp.account.login

import com.example.templateapp.base.BaseViewState

data class LoginState(
        val loading: Boolean,
        val buttonState: LoginButtonState
): BaseViewState()

data class LoginButtonState(
        val buttonText: String,
        val enabled: Boolean)
