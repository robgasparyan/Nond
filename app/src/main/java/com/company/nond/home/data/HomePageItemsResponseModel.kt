package com.company.nond.home.data

data class HomePageItemsResponseModel(
    val pagination: Pagination?,
    val results: List<Result>
) {
    data class Pagination(
        val key: Any?
    )

    data class Result(
        val created_at: String?,
        val image_ids: List<String?>?,
        val image_urls: List<String?>?,
        val image_urls_thumbnails: List<String?>?,
        val name: String?,
        val price: String?,
        val uid: String?
    )
}