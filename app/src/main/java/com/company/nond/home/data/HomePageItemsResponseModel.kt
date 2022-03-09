package com.company.nond.home.data

data class HomePageItemsResponseModel(
    val pagination: Pagination?,
    val results: List<Result>
) {
    data class Pagination(
        val key: Any?
    )

    data class Result(
        val created_at: String? = null,
        val image_ids: List<String>? = null,
        val image_urls: List<String>? = null,
        val image_urls_thumbnails: List<String>? = null,
        val name: String? = null,
        val price: String? = null,
        val uid: String? = null
    )
}