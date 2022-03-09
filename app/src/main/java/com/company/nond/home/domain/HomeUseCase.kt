package com.company.nond.home.domain

import com.company.nond.home.data.HomeRepository
import com.company.nond.utils.UIModel
import com.company.nond.utils.map
import com.company.nond.utils.mappers.HomePageItemMapper
import com.company.nond.utils.toUIModel
import javax.inject.Inject


class HomeUseCase @Inject constructor(
    private val homeRepository: HomeRepository,
    private val homePageItemMapper: HomePageItemMapper
) {
    suspend fun loadHomePageData(): UIModel<List<HomePageItemsUIModel>> {
        return homeRepository.loadHomePageData().toUIModel().map {
            it.results.map { results ->
                homePageItemMapper.mapHomePageItem(results)
            }
        }
    }
}