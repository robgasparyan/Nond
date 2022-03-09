package com.company.nond.home.domain


data class HomePageItemsUIModel(
    val image_urls: List<String>?,
    val image_urls_thumbnails: List<String>?,
    val name: String,
    val price: String,
)