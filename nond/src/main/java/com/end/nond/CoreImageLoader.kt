package com.end.nond

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.ImageView
import com.end.nond.exceptions.HttpException
import com.end.nond.extensions.await
import com.end.nond.extensions.log
import com.end.nond.logger.Logger
import kotlinx.coroutines.*
import okhttp3.Call
import okhttp3.Request

class CoreImageLoader(
    private val callFactory: Call.Factory,
    private val logger: Logger?
) : ImageLoader {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO +
            CoroutineExceptionHandler { _, throwable -> logger?.log(TAG, throwable) })

    override fun enqueue(request: ImageRequest) {
        scope.launch {
            val imageCache = request.imageCache
            val url = checkNotNull(request.url) { "Null response body!" }
            if (imageCache?.hasItem(url) == true) {
                logger?.log(TAG) {
                    "Fill from weak memory"
                }
                withContext(Dispatchers.Main) {
                    setBitmapToImageView(request.imageView, imageCache.get(url))
                }
            } else {
                logger?.log(TAG) {
                    "New Call --> ${request.url}"
                }
                val request1 = Request.Builder().url(url)
                val response = callFactory.newCall(request1.build()).await()
                if (!response.isSuccessful) {
                    response.body?.close()
                    throw HttpException(response)
                }
                val body = checkNotNull(response.body) { "Null response body!" }
                val bitmap = BitmapFactory.decodeStream(body.byteStream())
                imageCache?.put(request.url, bitmap)
                withContext(Dispatchers.Main) {
                    setBitmapToImageView(request.imageView, bitmap)
                }
            }
        }
    }

    private suspend fun setBitmapToImageView(imageView: ImageView?, bitmap: Bitmap) =
        withContext(Dispatchers.Main) {
            val targetImageView =
                checkNotNull(imageView) { "Target View must not be null!" }
            targetImageView.setImageBitmap(bitmap)
        }

    companion object {
        private const val TAG = "ImageLoader"
    }
}