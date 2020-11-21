package com.example.templateapp.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer


abstract class BaseViewModelActivity<VM: BaseViewModel<VS, VA>, VS: BaseViewState, VA: BaseViewAction>: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.run {
            actionLiveData.observe(this@BaseViewModelActivity, Observer { handleAction(it) })
            stateLiveData.observe(this@BaseViewModelActivity, Observer { renderFreshState(it) })
        }
    }

    abstract val viewModel: VM

    abstract fun renderFreshState(freshState: VS)

    abstract fun handleAction(action: VA)
}
