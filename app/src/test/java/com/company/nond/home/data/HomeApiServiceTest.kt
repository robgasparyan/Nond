package com.company.nond.home.data

import com.company.nond.utils.createApi
import com.company.nond.utils.network.NetworkResponse
import com.end.testingUtils.rule.MockWebServerRule
import com.end.testingUtils.rule.createEmptyClient
import com.end.testingUtils.rule.enqueueResourceResponse
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.net.HttpURLConnection

class HomeApiServiceTest {
    @get:Rule
    val mockServerRule = MockWebServerRule()

    private lateinit var apiServiceServer: HomeApiService

    private fun provideCartApi(url: String): HomeApiService {
        return createApi(
            url,
            createEmptyClient(),
            HomeApiService::class.java
        )
    }

    @Before
    fun setup() {
        apiServiceServer = provideCartApi(
            mockServerRule.server.url("cart/").toString()
        )
    }

    @Test
    fun checkSetupPaymentSheetApi() = runTest {
        val jsonPath = "api-response/nond-testApi.json"

        mockServerRule.server.enqueueResourceResponse(
            jsonPath,
            HttpURLConnection.HTTP_OK
        )
        val response = apiServiceServer.loadHomePageItems()
        assert(response is NetworkResponse.Success)
        assert((response as NetworkResponse.Success).body.results.isNotEmpty())
    }
}