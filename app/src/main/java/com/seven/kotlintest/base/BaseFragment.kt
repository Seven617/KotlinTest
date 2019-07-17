package com.seven.kotlintest.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.debug
import org.jetbrains.anko.runOnUiThread
import org.jetbrains.anko.toast

abstract class BaseFragment : Fragment(), AnkoLogger {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        debug { "呵呵" }
    }

    /**
     * 初始化
     */
    protected open fun init() {

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return initView()
    }

    /**
     * 获取布局Id
     */
    abstract fun initView(): View?

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initListener()
        initData()
    }

    /**
     * 数据初始化
     */
    protected open fun initData() {

    }

    /**
     * adapter listener
     */
    protected open fun initListener() {

    }

    fun myToast(msg: String) {
        context?.runOnUiThread { toast(msg) }
    }

}