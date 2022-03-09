package com.company.nond

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

abstract class BaseViewModel  : ViewModel() {
    protected val _errorStateFlow = MutableStateFlow("")
    protected val _isLoadingStateFlow = MutableStateFlow(false)

    fun setErrorMessage(msg: String) {
        _errorStateFlow.value = msg
    }

    fun setIsLoadingValue(isLoading: Boolean) {
        _isLoadingStateFlow.value = isLoading
    }
}