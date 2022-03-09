package com.end.nond.extensions

import android.app.ActivityManager
import android.content.Context
import android.content.pm.ApplicationInfo
import androidx.core.content.getSystemService

private const val DEFAULT_MEMORY_CLASS_MEGABYTES = 256
private const val STANDARD_MULTIPLIER = 0.2
private const val LOW_MEMORY_MULTIPLIER = 0.15

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