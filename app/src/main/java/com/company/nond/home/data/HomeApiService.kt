package com.company.nond.home.data

import com.company.nond.utils.network.NetworkResponse
import retrofit2.http.GET


interface HomeApiService {
    @GET("/default/dynamodb-writer")
    suspend fun loadHomePageItems(): NetworkResponse<HomePageItemsResponseModel>
}