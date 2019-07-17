package com.seven.kotlintest.base


interface BaseView<RESPONSE> {

    /**
     * 获取数据失败
     */
    fun onError(message: String?)

    /**
     * 加载/初始化数据成功
     */
    fun loadSuccess(result: RESPONSE?)

    /**
     * 加载更多数据成功
     */
    fun loadMore(result: RESPONSE?)
}