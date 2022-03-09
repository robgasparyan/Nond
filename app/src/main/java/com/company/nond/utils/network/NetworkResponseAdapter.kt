package com.company.nond.utils.network

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Converter
import java.lang.reflect.Type


class NetworkResponseAdapter<S : Any, E : Any>(
    private val successType: Type,
    private val errorBodyConverter: Converter<ResponseBody, E>
) : CallAdapter<S, Call<NetworkResponse<S>>> {
    override fun responseType(): Type = successType

    override fun adapt(call: Call<S>) = NetworkResponseCall(call, errorBodyConverter)
}