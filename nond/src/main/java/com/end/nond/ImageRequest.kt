package com.end.nond

import android.content.Context
import android.widget.ImageView
import com.end.nond.cache.ImageCache
import com.end.nond.logger.DebugLogger
import com.end.nond.logger.Logger

class ImageRequest private constructor(
    val context: Context,
    val url: String?,
    val cache: CachePolicy = CachePolicy.ENABLED,
    var imageView: ImageView? = null,
    var imageCache: ImageCache?,
    val logger: Logger? = DebugLogger()
) {

    class Builder {
        private lateinit var context: Context
        private var url: String? = null
        private var cachePolicy: CachePolicy = CachePolicy.ENABLED
        private var imageView: ImageView? = null
        private var imageCache: ImageCache? = null
        private var logger: Logger? = DebugLogger()

        fun setContext(context: Context) = apply {
            this.context = context
        }

        fun setUrl(url: String?) = apply {
            this.url = url
        }

        fun setCacheManager(imageCache: ImageCache) = apply {
            this.imageCache = imageCache
        }

        fun setImageView(imageView: ImageView) = apply {
            this.imageView = imageView
        }

        fun setCachePolicy(cachePolicy: CachePolicy) = apply {
            this.cachePolicy = cachePolicy
        }

        fun build(): ImageRequest {
            return ImageRequest(
                context, url, cachePolicy, imageView, imageCache, logger
            )
        }
    }
}