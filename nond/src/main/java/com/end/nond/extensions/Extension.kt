package com.end.nond.extensions

import android.content.Context
import android.webkit.MimeTypeMap
import android.widget.ImageView
import com.end.nond.ImageLoader
import com.end.nond.ImageRequest
import com.end.nond.Nond
import com.end.nond.cache.ImageCache

internal fun MimeTypeMap.getMimeTypeFromUrl(url: String?): String? {
    if (url.isNullOrBlank()) {
        return null
    }

    val extension = url
        .substringBeforeLast('#') // Strip the fragment.
        .substringBeforeLast('?') // Strip the query.
        .substringAfterLast('/') // Get the last path segment.
        .substringAfterLast('.', missingDelimiterValue = "") // Get the file extension.

    return getMimeTypeFromExtension(extension)
}

fun ImageView.loadImage(
    url: String?,
    imageLoader: ImageLoader = context.imageLoader,
) {
    val request = ImageRequest.Builder()
        .setContext(context)
        .setUrl(url)
        .setCacheManager(context.imageCache)
        .setImageView(this)
        .build()
    return imageLoader.enqueue(request)
}

inline val Context.imageLoader: ImageLoader
    get() = Nond.getImageLoader(this)

inline val Context.imageCache: ImageCache
    get() = Nond.getImageCache(this)

internal inline val Any.identityHashCode: Int
    get() = System.identityHashCode(this)