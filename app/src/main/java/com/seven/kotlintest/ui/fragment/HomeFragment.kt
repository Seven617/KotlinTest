package com.seven.kotlintest.ui.fragment

import com.seven.kotlintest.adapter.HomeAdapter
import com.seven.kotlintest.base.BaseListAdapter
import com.seven.kotlintest.base.BaseListFragmnet
import com.seven.kotlintest.base.BaseListPresenter
import com.seven.kotlintest.model.HomeItemBean
import com.seven.kotlintest.presenter.impl.HomePresenterImpl
import com.seven.kotlintest.ui.activity.WebViewActivity
import com.seven.kotlintest.view.HomeView
import com.seven.kotlintest.widget.HomeItemView
import org.jetbrains.anko.support.v4.startActivity

class HomeFragment : BaseListFragmnet<List<HomeItemBean>, HomeItemBean, HomeItemView>(), HomeView {

    var lists = ArrayList<HomeItemBean>()
    override fun getSpecialAdapter(): BaseListAdapter<HomeItemBean, HomeItemView> {
        return HomeAdapter()
    }


    override fun getSpecialPresenter(): BaseListPresenter {
        return HomePresenterImpl(this)
    }

    override fun getList(response: List<HomeItemBean>?): List<HomeItemBean>? {
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