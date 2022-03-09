package com.company.nond.home.presentation

import app.cash.turbine.test
import com.company.nond.home.domain.HomeUseCase
import com.company.nond.utils.UIModel
import com.end.testingUtils.rule.CoroutineRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import junit.framework.Assert.*
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {

    @get:Rule
    val coroutineRule = CoroutineRule()

    @MockK
    lateinit var homeUseCase: HomeUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `check UI loader switcher`() = runTest {
        val homeViewModel = HomeViewModel(homeUseCase)
        homeViewModel.setIsLoadingValue(true)
        homeViewModel.isLoadingStateFlow.test {
            assertTrue(awaitItem())
        }
    }

    @Test
    fun `check loading state once load home page data process started failure case`() = runTest {
        val homeViewModel = HomeViewModel(homeUseCase)
        homeViewModel.loadHomePageData()
        coEvery { homeUseCase.loadHomePageData() } returns UIModel.Fail(Exception())
        homeViewModel.isLoadingStateFlow.test {
            assertFalse(awaitItem())
        }
    }

    @Test
    fun `check loading state once load home page data process started succeed case`() = runTest {
        val homeViewModel = HomeViewModel(homeUseCase)
        homeViewModel.loadHomePageData()
        coEvery { homeUseCase.loadHomePageData() } returns UIModel.Data(emptyList())
        homeViewModel.isLoadingStateFlow.test {
            assertFalse(awaitItem())
        }
    }

    @Test
    fun `check home page data success case`() = runTest {
        val homeViewModel = HomeViewModel(homeUseCase)
        homeViewModel.loadHomePageData()
        coEvery { homeUseCase.loadHomePageData() } returns UIModel.Data(emptyList())
        homeViewModel.homePageDataList.test {
            assertTrue(awaitItem().isEmpty())
        }
    }

    @Test
    fun `check home page data failure case`() = runTest {
        val homeViewModel = HomeViewModel(homeUseCase)
        val exceptionText = "Test Exception"
        coEvery { homeUseCase.loadHomePageData() } returns UIModel.Fail(Exception(exceptionText))
        homeViewModel.loadHomePageData()
        homeViewModel.errorStateFlow.test {
            // default empty value
            awaitItem()
            val throwableText = awaitItem()
            assertEquals(throwableText, exceptionText)
        }
    }
}