package com.end.testingUtils.rule

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.nio.charset.StandardCharsets

inline fun <reified T> safeCast(instance: Any?, block: T.() -> Unit) {
    if (instance is T) {
        block(instance)
    }
}

fun MockWebServer.enqueueResourceResponse(fileName: String, code: Int) {
    val inputStream = javaClass.classLoader!!.getResourceAsStream(fileName)!!
    enqueue(
        MockResponse()
            .setResponseCode(code)
            .setBody(inputStream.source().buffer().readString(StandardCharsets.UTF_8))
    )
}

fun <T> createApi(
    baseUrl: String,
    client: OkHttpClient,
    clazz: Class<T>
): T {
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(Gson()))
        .build()
        .create(clazz)
}

fun createEmptyClient(): OkHttpClient {
    return OkHttpClient.Builder().build()
}

fun <T : Any> T.setPrivateProperty(variableName: String, data: Any): Any? {
    return javaClass.getDeclaredField(variableName).let { field ->
        field.isAccessible = true
        field.set(this, data)
        return@let field.get(this)
    }
}
