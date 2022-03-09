package com.company.nond.utils

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.company.nond.MainActivity
import com.company.nond.utils.network.NetworkResponseAdapterFactory
import com.google.gson.Gson
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
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

fun <T> Flow<T>.collectWhileStarted(
    lifecycleOwner: LifecycleOwner,
    action: suspend (value: T) -> Unit
) {
    var job: Job? = null
    lifecycleOwner.lifecycle.addObserver(LifecycleEventObserver { source, event ->
        when (event) {
            Lifecycle.Event.ON_START -> {
                job = source.lifecycleScope.launch {
                    collect(action)
                }
            }
            Lifecycle.Event.ON_STOP -> {
                job?.cancel()
                job = null
            }
            else -> {
            }
        }
    })
}