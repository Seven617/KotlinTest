package com.seven.kotlintest.base

import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.seven.kotlintest.R
import kotlinx.android.synthetic.main.fragment_home.*

abstract class BaseListFragment<RESPONSE, ITEMBEAN, ITEMVIEW : View> : BaseFragment(), BaseView<RESPONSE> {

    val adapter by lazy { getSpecialAdapter() }
    val presenter by lazy { getSpecialPresenter() }

    override fun onError(message: String?) {
        myToast("加载数据失败")
    }

    override fun loadSuccess(result: RESPONSE?) {
        refreshLayout.isRefreshing = false
        adapter.updateList(getList(BaseListPresenter.TYPE_INIT_REFRESH,result))
    }

    override fun loadMore(result: RESPONSE?) {
        adapter.loadMore(getList(BaseListPresenter.TYPE_LOAD_MORE,result))
    }

    override fun initView(): View? {
        return View.inflate(context, R.layout.fragment_list_base, null)
    }

    override fun initData() {
        presenter.loadDatas()
    }

    override fun initListener() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        refreshLayout.setColorSchemeColors(Color.RED, Color.YELLOW, Color.GREEN)
        refreshLayout.setOnRefreshListener {
            presenter.loadDatas()
        }

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    val layoutManager = recyclerView.layoutManager
                    if (layoutManager is LinearLayoutManager) {
                        val lastPotion = layoutManager.findLastVisibleItemPosition()
                        if (lastPotion == adapter.itemCount - 1) {
                            presenter.loadMore(adapter.itemCount)
                        }
                    }
                }
            }

        })
    }


    abstract fun getSpecialAdapter(): BaseListAdapter<ITEMBEAN, ITEMVIEW>

    abstract fun getSpecialPresenter(): BaseListPresenter

    abstract fun getList(type: Int,response: RESPONSE?): List<ITEMBEAN>?
}


