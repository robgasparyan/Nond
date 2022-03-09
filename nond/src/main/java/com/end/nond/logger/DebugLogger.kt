package com.end.nond.logger

import android.util.Log

class DebugLogger : Logger {
    override var level: Int = Log.DEBUG

    override fun log(tag: String, message: String?, throwable: Throwable?) {
        message?.let {
            Log.println(Log.INFO, tag, message)
        }
    }
}