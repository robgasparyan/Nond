package com.end.nond.extensions

import com.end.nond.logger.Logger

inline fun Logger.log(tag: String, lazyMessage: () -> String) {
    log(tag, lazyMessage(), null)
}

fun Logger.log(tag: String, throwable: Throwable) {
    log(tag, null, throwable)
}