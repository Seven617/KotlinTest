package com.seven.kotlintest.base

interface BaseListPresenter {
    companion object {
        const val TYPE_INIT_REFRESH = 1
        const val TYPE_LOAD_MORE = 2
    }

    fun loadDatas()
    fun loadMore(itemCount: Int)

    /**
     * 解绑操作
     */
    fun destoryView()
}