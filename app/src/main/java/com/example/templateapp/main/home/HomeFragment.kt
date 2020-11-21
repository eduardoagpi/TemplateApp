package com.example.templateapp.main.home

import androidx.fragment.app.viewModels
import com.example.templateapp.R
import com.example.templateapp.base.BaseViewModelFragment
import com.example.templateapp.base.extensions.shortToast
import com.example.templateapp.main.home.HomeActions.SayHi
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseViewModelFragment<HomeViewModel, HomeState, HomeActions>() {

    override val viewModel by viewModels<HomeViewModel>()

    override fun getFragmentLayout() = R.layout.fragment_home

    override fun onStart() {
        super.onStart()
        viewModel.sayHi()
    }

    override fun renderFreshState(freshState: HomeState) { }

    override fun handleAction(action: HomeActions) = when(action) {
        is SayHi -> shortToast(action.hi)
    }

}
