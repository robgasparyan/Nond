package com.company.nond.utils

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.company.nond.MainActivity
import com.company.nond.utils.network.NetworkResponseAdapterFactory
import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun showLongToast(text: String, context: Context) {
    Toast.makeText(context, text, Toast.LENGTH_LONG).show()
}

fun Fragment.showLoading() {
    activity?.let { (it as MainActivity).showLoading() }
}

fun Fragment.hideLoading() {
    activity?.let { (it as MainActivity).hideLoading() }
}

fun <T> createApi(
    baseUrl: String,
    client: OkHttpClient,
    clazz: Class<T>
): T {
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(client)
        .addCallAdapterFactory(NetworkResponseAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create(Gson()))
        .build()
        .create(clazz)
}