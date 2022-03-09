package com.end.nond

import android.content.Context
import com.end.nond.cache.ImageCache
import okhttp3.OkHttpClient

object Nond {
    private var imageLoader: ImageLoader? = null
    private var imageCache: ImageCache? = null

    fun getImageLoader(context: Context): ImageLoader = imageLoader ?: newImageLoader(context)
    fun getImageCache(context: Context): ImageCache = imageCache ?: newImageCache(context)

    private fun newImageCache(context: Context) = let {
        val imageCache = ImageCache.Builder().setMaxSize(0.25).setContext(context).build()
        this.imageCache = imageCache
        imageCache
    }

    private fun newImageLoader(context: Context) = let {
        val imageLoader =
            ImageLoader.Builder()
                .setContext(context)
                .setOkHttpClient(buildDefaultCallFactory())
                .build()
        this.imageLoader = imageLoader
        imageLoader
    }

    private fun buildDefaultCallFactory() = let {
        OkHttpClient.Builder()
            .build()
    }
}