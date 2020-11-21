package com.example.templateapp.account.login

import com.example.templateapp.base.BaseViewAction

sealed class LoginActions: BaseViewAction() {
    data class ShowToastErrorMessage(val errorMessage: String): LoginActions()
    object NavigateToHome: LoginActions()
}

