package com.example.templateapp.main

import android.content.res.Resources
import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.templateapp.R

class MainActivityViewModel @ViewModelInject constructor(
    private val resources: Resources): ViewModel() {

    fun bark() {
        Log.v("TAG", resources.getString(R.string.app_name))
    }
}
