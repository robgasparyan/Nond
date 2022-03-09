package com.end.nond.cache

import android.content.Context
import android.graphics.Bitmap
import androidx.annotation.FloatRange
import com.end.nond.exceptions.ImageCacheException
import com.end.nond.extensions.calculateAvailableMemorySize

interface ImageCache {
    fun put(key: String, bitmap: Bitmap)
    fun get(key: String): Bitmap
    fun hasItem(key: String):Boolean
    fun remove(key: String)
    fun clearCache()

    class Builder {
        private var context: Context? = null
        private var maxSize: Double = 0.0

        fun setMaxSize(@FloatRange(from = 0.0, to = 1.0) percent: Double) = apply {
            this.maxSize = percent
        }

        fun setContext(context: Context) = apply {
            this.context = context
        }

        fun build(): CoreImageCache {
            return CoreImageCache(calculateAvailableMemorySize(context!!, maxSize))
        }
    }
}