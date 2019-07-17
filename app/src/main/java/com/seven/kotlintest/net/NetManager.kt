package com.seven.kotlintest.net

import com.seven.kotlintest.util.ThreadUtil
import okhttp3.*
import java.io.IOException

/**
 * 发送网络请求的类
 */
class NetManager private constructor() {

    private val client by lazy { OkHttpClient() }

    companion object {
        val manager by lazy { NetManager() }
    }

    /**
     * 发送请求
     */
    fun <RESPONSE> sendRequest(req: MRequest<RESPONSE>) {
        val request = Request.Builder().url(req.url).get().build()
        client.newCall(request).enqueue(object : Callback {
            /**
             * 子线程调用
             */
            override fun onFailure(call: Call, e: IOException) {
                //回调到View层进行处理
                req.handler.onError(req.type,e.message)
            }

            /**
             * 子线程调用
             */
            override fun onResponse(call: Call, response: Response) {
                val str = response.body?.string()
                val result = str?.substring(29, str.length - 2)
                //println("网址返回的参数是:$result")
                val parseResult = req.parseResult(result)
                ThreadUtil.runOnMainthread(Runnable {
                    //将结果回调给View层
                    req.handler.onSuccess(req.type,parseResult)
                })
            }

        })
    }
}