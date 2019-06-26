package com.seven.kotlintest.base

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.debug
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

abstract class BaseActivity : AppCompatActivity(), AnkoLogger {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initListener()
        initData()
        debug { "哈哈" }
    }

    /**
     * 获取布局ID
     * */
    abstract fun getLayoutId(): Int

    /**
     * adapter listener
     */
    protected open fun initListener() {}

    /**
     * 初始化数据
     */
    protected open fun initData() {}


    /**
     * Toast
     */
    protected fun myToast(msg: String) {
        runOnUiThread { toast(msg) }
    }

    inline fun <reified T : Activity> startActivityAndFinish() {
        startActivity<T>()
        finish()
    }
}