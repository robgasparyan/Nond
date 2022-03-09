package com.end.nond

import android.content.Context
import com.end.nond.logger.DebugLogger
import okhttp3.OkHttpClient

interface ImageLoader {
    fun enqueue(request: ImageRequest)

    class Builder {
        private var okHttpClient: OkHttpClient? = null
        private var context: Context? = null

        fun setOkHttpClient(okHttpClient: OkHttpClient) = apply {
            this.okHttpClient = okHttpClient
        }

        fun setContext(context: Context) = apply {
            this.context = context
        }

        fun build() = CoreImageLoader(okHttpClient!!, DebugLogger())
    }
}