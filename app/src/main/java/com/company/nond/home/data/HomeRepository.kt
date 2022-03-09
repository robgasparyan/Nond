package com.company.nond.home.data

import com.company.nond.utils.network.NetworkResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import javax.inject.Inject


class HomeRepository @Inject constructor(private val homeApiService: HomeApiService) {

    suspend fun loadHomePageData(): NetworkResponse<HomePageItemsResponseModel> =
        coroutineScope {
            withContext(Dispatchers.IO) {
                homeApiService.loadHomePageItems()
            }
        }
}