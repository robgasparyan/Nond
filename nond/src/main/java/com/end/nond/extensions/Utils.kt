package com.end.nond.extensions

import android.app.ActivityManager
import android.content.Context
import android.content.pm.ApplicationInfo
import android.os.StatFs
import androidx.core.content.getSystemService
import okhttp3.Cache
import java.io.File

private const val DEFAULT_MEMORY_CLASS_MEGABYTES = 256
private const val STANDARD_MULTIPLIER = 0.2
private const val LOW_MEMORY_MULTIPLIER = 0.15

private const val MIN_DISK_CACHE_SIZE_BYTES = 10L * 1024 * 1024 // 10MB
private const val MAX_DISK_CACHE_SIZE_BYTES = 250L * 1024 * 1024 // 250MB
private const val DISK_CACHE_PERCENTAGE = 0.02

fun calculateAvailableMemorySize(context: Context, percentage: Double): Int {
    val memoryClassMegabytes = try {
        val activityManager: ActivityManager = context.requireSystemService()
        val isLargeHeap = (context.applicationInfo.flags and ApplicationInfo.FLAG_LARGE_HEAP) != 0
        if (isLargeHeap) activityManager.largeMemoryClass else activityManager.memoryClass
    } catch (_: Exception) {
        DEFAULT_MEMORY_CLASS_MEGABYTES
    }
    return (percentage * memoryClassMegabytes * 1024 * 1024).toInt()
}

fun getDefaultAvailableMemoryPercentage(context: Context?): Double {
    return try {
        val activityManager: ActivityManager = context?.requireSystemService()!!
        if (activityManager.isLowRamDevice) LOW_MEMORY_MULTIPLIER else STANDARD_MULTIPLIER
    } catch (_: Exception) {
        STANDARD_MULTIPLIER
    }
}

internal inline fun <reified T : Any> Context.requireSystemService(): T {
    return checkNotNull(getSystemService()) { "System service of type ${T::class.java} was not found." }
}

private const val CACHE_DIRECTORY_NAME = "nond_image_cache"

fun createDefaultCache(context: Context): Cache {
    val cacheDirectory = getDefaultCacheDirectory(context)
    val cacheSize = calculateDiskCacheSize(cacheDirectory)
    return Cache(cacheDirectory, cacheSize)
}

fun getDefaultCacheDirectory(context: Context): File {
    return File(context.cacheDir, CACHE_DIRECTORY_NAME).apply { mkdirs() }
}

fun calculateDiskCacheSize(cacheDirectory: File): Long {
    return try {
        val cacheDir = StatFs(cacheDirectory.absolutePath)
        val size = DISK_CACHE_PERCENTAGE * cacheDir.blockCountLong * cacheDir.blockSizeLong
        return size.toLong().coerceIn(MIN_DISK_CACHE_SIZE_BYTES, MAX_DISK_CACHE_SIZE_BYTES)
    } catch (_: Exception) {
        MIN_DISK_CACHE_SIZE_BYTES
    }
}