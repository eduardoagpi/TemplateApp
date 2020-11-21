package com.example.templateapp.main.home

import com.example.templateapp.base.BaseViewAction

sealed class HomeActions: BaseViewAction() {
    data class SayHi(val hi: String): HomeActions()
}
