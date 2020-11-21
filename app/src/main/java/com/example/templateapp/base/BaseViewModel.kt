package com.example.templateapp.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class BaseViewModel<VS: BaseViewState, VA: BaseViewAction> : ViewModel() {

    private val _stateLiveData = MutableLiveData<VS>()
    val stateLiveData: LiveData<VS>
        get() = _stateLiveData

    private val _actionLiveData = SingleLiveEvent<VA>()
    val actionLiveData: LiveData<VA>
        get() = _actionLiveData

    protected abstract fun determineState(): VS

    protected fun postFreshState() {
        viewModelScope.launch(Dispatchers.Main) {
            _stateLiveData.value = determineState()
        }
    }

    protected fun postAction(action: VA) {
        viewModelScope.launch(Dispatchers.Main) {
            _actionLiveData.value = action
        }
    }
}
