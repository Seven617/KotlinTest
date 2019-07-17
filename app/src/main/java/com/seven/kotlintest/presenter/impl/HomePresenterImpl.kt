package com.seven.kotlintest.presenter.impl

import com.seven.kotlintest.base.BaseListPresenter
import com.seven.kotlintest.model.HomeItemBean
import com.seven.kotlintest.net.HomeRequest
import com.seven.kotlintest.net.ResponseHandler
import com.seven.kotlintest.presenter.interf.HomePresenter
import com.seven.kotlintest.view.HomeView

class HomePresenterImpl(var homeView: HomeView?) : HomePresenter, ResponseHandler<List<HomeItemBean>> {

    /**
     * 初始化数据
     */
    override fun loadDatas() {

        //定义reQuest
        HomeRequest(BaseListPresenter.TYPE_INIT_REFRESH, 1, this).execute()
    }

    override fun loadMore(itemCount: Int) {

        //定义reQuest
        HomeRequest(BaseListPresenter.TYPE_LOAD_MORE, itemCount, this).execute()

    }


    override fun onError(type: Int, msg: String?) {
        homeView?.onError(msg)
    }

    override fun onSuccess(type: Int, result: List<HomeItemBean>) {
        //区分初始化数据还是加载更多数据
        if (type == BaseListPresenter.TYPE_INIT_REFRESH) {
            homeView?.loadSuccess(result)
        } else if (type == BaseListPresenter.TYPE_LOAD_MORE) {
            homeView?.loadMore(result)
        }
//        when (type) {
//            BaseListPresenter.TYPE_INIT_REFRESH -> homeView?.loadSuccess(result)
//            BaseListPresenter.TYPE_LOAD_MORE -> homeView?.loadMore(result)
//        }
    }

    /**
     * 解绑操作
     */
    override fun destoryView() {
        if (homeView != null) {
            homeView = null
        }
    }
}