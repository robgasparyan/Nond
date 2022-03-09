package com.company.nond.utils.mappers

import com.company.nond.home.data.HomePageItemsResponseModel
import com.company.nond.home.domain.HomePageItemsUIModel
import javax.inject.Inject

class HomePageItemMapper @Inject constructor() {
    fun mapHomePageItem(homePageItemsResponseModel: HomePageItemsResponseModel.Result?) =
        HomePageItemsUIModel(
            homePageItemsResponseModel?.image_urls,
            homePageItemsResponseModel?.image_urls_thumbnails,
            //both can be replaced with some placeholders
            homePageItemsResponseModel?.name.orEmpty(),
            homePageItemsResponseModel?.price.orEmpty()
        )
}