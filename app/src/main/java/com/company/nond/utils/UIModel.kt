package com.company.nond.utils

import com.company.nond.utils.network.NetworkResponse


sealed class UIModel<out T : Any> {
    class Data<out T : Any>(val data: T) : UIModel<T>()
    class Fail<out T : Any>(val t: Throwable) : UIModel<T>()

}

fun <T : Any, K : Any> UIModel<T>.map(block: (T) -> K): UIModel<K> {
    return when (this) {
        is UIModel.Data -> UIModel.Data(block(this.data))
        is UIModel.Fail -> UIModel.Fail(this.t)
    }
}

fun <T : Any, K : Any> UIModel<T>.mapData(success: (T) -> K, other: () -> K): UIModel<K> {
    return when (this) {
        is UIModel.Data -> UIModel.Data(success(this.data))
        else -> UIModel.Data(other())
    }
}

fun <T : Any> UIModel<T>.dataOrNull(): T? {
    return (this as? UIModel.Data<T>)?.data
}

fun <T : Any> UIModel<T>.dataOrDefault(default: T): T {
    return (this as? UIModel.Data<T>)?.data ?: default
}

fun <T : Any> UIModel<T>.dataOrElse(block: () -> T): T {
    return (this as? UIModel.Data<T>)?.data ?: block()
}

fun <T : Any> UIModel<T>.dataOrException(): T {
    return (this as UIModel.Data).data
}

fun <T : Any> NetworkResponse<T>.toUIModel(): UIModel<T> {
    return when (this) {
        is NetworkResponse.Success -> UIModel.Data(body)
        is NetworkResponse.Fail -> UIModel.Fail(t)
    }
}
