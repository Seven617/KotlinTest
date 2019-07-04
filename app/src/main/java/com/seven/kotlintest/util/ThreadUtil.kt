package com.seven.kotlintest.util

import android.os.Handler
import android.os.Looper

object ThreadUtil {
    val handler = Handler(Looper.getMainLooper())

    fun runOnMainthread(runnable: Runnable) {
        handler.post(runnable)
    }
}