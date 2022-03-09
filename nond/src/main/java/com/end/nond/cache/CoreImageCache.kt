package com.end.nond.cache

import android.graphics.Bitmap
import android.util.LruCache
import com.end.nond.extensions.identityHashCode

class CoreImageCache(
    maxSize: Int
) : ImageCache {

    private val lruCache = object : LruCache<Int, Bitmap>(maxSize) {
        override fun sizeOf(key: Int?, value: Bitmap): Int {
            return value.byteCount
        }
    }

    @Synchronized
    override fun put(key: String, bitmap: Bitmap) {
        lruCache.put(key.identityHashCode, bitmap)
    }

    @Synchronized
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