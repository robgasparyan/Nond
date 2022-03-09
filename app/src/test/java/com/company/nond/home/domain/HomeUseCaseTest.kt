package com.company.nond.home.domain

import com.company.nond.home.data.HomePageItemsResponseModel
import com.company.nond.home.data.HomeRepository
import com.company.nond.utils.mappers.HomePageItemMapper
import com.company.nond.utils.network.NetworkResponse
import com.end.testingUtils.rule.CoroutineRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class HomeUseCaseTest {
    @get:Rule
    val coroutineRule = CoroutineRule()

    @Test
    fun `Check useCase Repo call`() = runTest {
        val homeRepo: HomeRepository = mockk()
        val useCase = HomeUseCase(homeRepo, HomePageItemMapper())
        coEvery { homeRepo.loadHomePageData() } returns NetworkResponse.Success(
            HomePageItemsResponseModel(
                null, emptyList()
            )
        )
        useCase.loadHomePageData()
        coVerify { homeRepo.loadHomePageData() }
    }

    @Test
    fun `Check useCase Mapper call`() = runTest {
        val homeRepo: HomeRepository = mockk()
        val mapper: HomePageItemMapper = spyk()
        val useCase = HomeUseCase(homeRepo, mapper)
        coEvery { homeRepo.loadHomePageData() } returns NetworkResponse.Success(
            HomePageItemsResponseModel(
                null, arrayListOf(
                    HomePageItemsResponseModel.Result()
                )
            )
        )
        useCase.loadHomePageData()
        coVerify { mapper.mapHomePageItem(any()) }
    }

    @Test
    fun `Check useCase load home page flow Success`() = runTest {
        val homeRepo: HomeRepository = mockk()
        val mapper: HomePageItemMapper = spyk()
        val useCase = HomeUseCase(homeRepo, mapper)
        val response = HomePageItemsResponseModel(
            null, arrayListOf(
                HomePageItemsResponseModel.Result(
                    created_at = "2019-02-24 04:04:17.566515",
                    image_ids = arrayListOf("image_ids"),
                    image_urls_thumbnails = arrayListOf("image_ids"),
                    name = "XYZ",
                    price = "141",
                    uid = "uid"
                )
            )
        )
        coEvery { homeRepo.loadHomePageData() } returns NetworkResponse.Success(
            response
        )
        useCase.loadHomePageData()
        coVerify { mapper.mapHomePageItem(response.results.first()) }
    }
}