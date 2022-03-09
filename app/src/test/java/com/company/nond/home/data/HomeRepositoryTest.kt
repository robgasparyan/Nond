package com.company.nond.home.data

import com.company.nond.utils.network.NetworkResponse
import com.end.testingUtils.rule.CoroutineRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class HomeRepositoryTest {
    @get:Rule
    val coroutineRule = CoroutineRule()

    @Test
    fun `check load home page data Failure`() = runTest {
        val apiService: HomeApiService = mockk()
        coEvery { apiService.loadHomePageItems() } returns NetworkResponse.Fail(Exception())
        val repo = HomeRepository(apiService)
        assert(repo.loadHomePageData() is NetworkResponse.Fail)
    }

    @Test
    fun `check load home page data Success`() = runTest {
        val apiService: HomeApiService = mockk()
        coEvery { apiService.loadHomePageItems() } returns NetworkResponse.Success(
            HomePageItemsResponseModel(null, emptyList())
        )
        val repo = HomeRepository(apiService)
        assert(repo.loadHomePageData() is NetworkResponse.Success)
    }

    @Test
    fun `check load home page data verification`() = runTest {
        val apiService: HomeApiService = mockk()
        coEvery { apiService.loadHomePageItems() } returns NetworkResponse.Fail(Exception())
        val repo = HomeRepository(apiService)
        repo.loadHomePageData()
        coVerify { apiService.loadHomePageItems() }
    }
}