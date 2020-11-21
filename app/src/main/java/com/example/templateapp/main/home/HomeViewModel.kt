package com.example.templateapp.main.home

import android.content.res.Resources
import androidx.hilt.lifecycle.ViewModelInject
import com.example.templateapp.R
import com.example.templateapp.base.BaseViewModel

class HomeViewModel @ViewModelInject constructor(private val resources: Resources): BaseViewModel<HomeState, HomeActions>() {

    fun sayHi() = postAction(HomeActions.SayHi(resources.getString(R.string.hi)))

    override fun determineState(): HomeState {
        TODO("Not yet implemented")
    }
}
