package com.end.nond.cache

import android.content.Context
import android.graphics.Bitmap
import android.util.LruCache
import com.end.nond.extensions.identityHashCode

class CoreImageCache(
    maxSize: Int
) : ImageCache {

    private val lruCache = LruCache<Int, Bitmap>(maxSize)

    override fun put(key: String, bitmap: Bitmap) {
        lruCache.put(key.identityHashCode, bitmap)
    }

    override fun get(key: String): Bitmap {
        return lruCache.get(key.identityHashCode)
    }

    override fun hasItem(key: String) = lruCache.get(key.identityHashCode) != null

    override fun remove(key: String) {
        lruCache.remove(key.identityHashCode)
    }

    override fun clearCache() {
        lruCache.evictAll()
    }
}