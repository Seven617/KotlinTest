package com.seven.kotlintest.presenter.impl

import com.seven.kotlintest.base.BaseListPresenter
import com.seven.kotlintest.model.NewsBean
import com.seven.kotlintest.net.NewsListRequest
import com.seven.kotlintest.net.ResponseHandler
import com.seven.kotlintest.presenter.interf.NewsListPresenter
import com.seven.kotlintest.view.NewsListView

class NewsListPresenterImpl(var code: String, var mvListView: NewsListView?) : NewsListPresenter,
    ResponseHandler<List<NewsBean>> {
    override fun onError(type: Int, msg: String?) {
        mvListView?.onError(msg)
    }

    override fun onSuccess(type: Int, result: List<NewsBean>) {
        when (type) {
            BaseListPresenter.TYPE_INIT_REFRESH -> mvListView?.loadSuccess(result)
            BaseListPresenter.TYPE_LOAD_MORE -> mvListView?.loadMore(result)
        }
    }

    override fun loadDatas() {
        NewsListRequest(BaseListPresenter.TYPE_INIT_REFRESH, code, 1, this).execute()
    }

    override fun loadMore(itemCount: Int) {
        NewsListRequest(BaseListPresenter.TYPE_LOAD_MORE, code, itemCount, this).execute()
    }

    override fun destoryView() {
        if(mvListView!=null){
            mvListView=null
        }
    }

}