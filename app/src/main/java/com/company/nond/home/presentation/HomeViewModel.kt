package com.company.nond.home.presentation

import androidx.lifecycle.viewModelScope
import com.company.nond.BaseViewModel
import com.company.nond.home.domain.HomePageItemsUIModel
import com.company.nond.home.domain.HomeUseCase
import com.company.nond.utils.UIModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val homeUseCase: HomeUseCase) : BaseViewModel() {
    val errorStateFlow: StateFlow<String> get() = _errorStateFlow.asStateFlow()
    val isLoadingStateFlow: StateFlow<Boolean> get() = _isLoadingStateFlow.asStateFlow()
    private val _homePageDataList = MutableStateFlow<List<HomePageItemsUIModel>>(emptyList())
    val homePageDataList: StateFlow<List<HomePageItemsUIModel>> get() = _homePageDataList.asStateFlow()

    fun loadHomePageData() {
        viewModelScope.launch {
            setIsLoadingValue(true)
            when (val dataUiModel = homeUseCase.loadHomePageData()) {
                is UIModel.Data -> {
                    setHomePageData(dataUiModel.data)
                    setIsLoadingValue(false)
                }
                is UIModel.Fail -> {
                    setErrorMessage(dataUiModel.t.toString())
                    setIsLoadingValue(false)
                }
            }
        }
    }

    private fun setHomePageData(data: List<HomePageItemsUIModel>) {
        _homePageDataList.value = data
    }


}