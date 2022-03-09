package com.end.nond.logger

interface Logger {
    var level: Int

    fun log(tag: String, message: String?, throwable: Throwable?)
}