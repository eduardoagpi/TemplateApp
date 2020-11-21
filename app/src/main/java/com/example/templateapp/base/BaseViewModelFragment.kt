package com.example.templateapp.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer

abstract class BaseViewModelFragment<VM: BaseViewModel<VS, VA>, VS: BaseViewState, VA: BaseViewAction> : Fragment() {

    protected abstract val viewModel: VM

    @LayoutRes
    abstract fun getFragmentLayout(): Int

    override fun onResume() {
        viewModel.run {
            actionLiveData.observe(this@BaseViewModelFragment, Observer { handleAction(it) })
            stateLiveData.observe(this@BaseViewModelFragment, Observer { renderFreshState(it) })
        }
        super.onResume()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getFragmentLayout(), container, false)
    }

    protected abstract fun renderFreshState(freshState: VS)

    protected abstract fun handleAction(action: VA)
}
