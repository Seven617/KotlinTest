package com.seven.kotlintest.ui.fragment

import com.seven.kotlintest.adapter.NewsListAdapter
import com.seven.kotlintest.base.BaseListAdapter
import com.seven.kotlintest.base.BaseListFragmnet
import com.seven.kotlintest.base.BaseListPresenter
import com.seven.kotlintest.model.NewsBean
import com.seven.kotlintest.presenter.impl.NewsListPresenterImpl
import com.seven.kotlintest.ui.activity.WebViewActivity
import com.seven.kotlintest.view.NewsListView
import com.seven.kotlintest.widget.NewsItemView
import org.jetbrains.anko.support.v4.startActivity

class NewsPagerFragment: BaseListFragmnet<List<NewsBean>, NewsBean, NewsItemView>(), NewsListView {

    var lists = ArrayList<NewsBean>()

    var code: String? = null

    override fun init() {
        code = arguments?.getString("args")
    }



    override fun getSpecialAdapter(): BaseListAdapter<NewsBean, NewsItemView> {
        return NewsListAdapter()
    }

    override fun getSpecialPresenter(): BaseListPresenter {
        return NewsListPresenterImpl(code!!,this)
    }

    override fun getList(response: List<NewsBean>?): List<NewsBean>? {
        lists.addAll(response!!)
        return response
    }

    override fun initListener() {
        super.initListener()
        //设置条目点击事件监听函数
        adapter.ItemClickListener(object :BaseListAdapter.ItemClickListener{
            override fun onItemClickListener(position: Int) {
                startActivity<WebViewActivity>("path" to lists[position].url)
            }

        })
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destoryView()
    }

}

