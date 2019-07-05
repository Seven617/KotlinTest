package com.seven.kotlintest.ui.fragment

import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.MotionEvent
import android.view.View
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.seven.kotlintest.R
import com.seven.kotlintest.adapter.HomeAdapter
import com.seven.kotlintest.adapter.OnItemClickListener
import com.seven.kotlintest.base.BaseFragment
import com.seven.kotlintest.model.Data
import com.seven.kotlintest.model.HomeBean
import com.seven.kotlintest.ui.activity.WebViewActivity
import com.seven.kotlintest.util.ThreadUtil
import com.seven.kotlintest.util.URLProviderUtils
import kotlinx.android.synthetic.main.fragment_home.*
import okhttp3.*
import org.jetbrains.anko.support.v4.startActivity
import java.io.IOException

class HomeFragment : BaseFragment() {

    val adapter by lazy { HomeAdapter() }
    var pageNumber: Int = 0
    private var lists = ArrayList<Data>()

    override fun initView(): View? {
        return View.inflate(context, R.layout.fragment_home, null)
    }

    override fun initData() {
        super.initData()
        loadDatas()
    }

    override fun initListener() {
        recyclerView.layoutManager = LinearLayoutManager(context)

        recyclerView.adapter = adapter

        refreshLayout.setColorSchemeColors(Color.RED, Color.YELLOW, Color.GREEN)

        refreshLayout.setOnRefreshListener {
            loadDatas()
        }
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    val layoutManager = recyclerView.layoutManager
                    if (layoutManager is LinearLayoutManager) {
                        val lastPotion = layoutManager.findLastVisibleItemPosition()
                        if (lastPotion == adapter.itemCount - 1) {
                            loadMore(adapter.itemCount - 1)
                        }
                    }
                }
            }

        })
        adapter.ItemClickListener(object : HomeAdapter.ItemClickListener {
            override fun onItemClickListener(position: Int) {
                myToast("点击的是:${lists[position].title}")
                startActivity<WebViewActivity>("path" to lists[position].path)
            }

        })
    }

    private fun loadDatas() {
        val path = URLProviderUtils.getHomeUrl(1,3)
        val client = OkHttpClient()
        val request = Request.Builder().url(path).get().build()
        client.newCall(request).enqueue(object : Callback {
            /**
             * 子线程调用
             */
            override fun onFailure(call: Call, e: IOException) {
                ThreadUtil.runOnMainthread(Runnable { refreshLayout.isRefreshing = false })
                myToast("获取数据失败")
            }

            /**
             * 子线程调用
             */
            override fun onResponse(call: Call, response: Response) {
                ThreadUtil.runOnMainthread(Runnable { refreshLayout.isRefreshing = false })
                myToast("获取数据成功")
                val result = response.body?.string()
                //println("获取数据成功" + result)
                val gson = Gson()
                val list = gson.fromJson(result, HomeBean::class.java)
                ThreadUtil.runOnMainthread(Runnable { adapter.updateList(list.result) })
                lists.addAll(list.result)
            }

        })
    }


    private fun loadMore(page: Int) {
        val path = URLProviderUtils.getHomeUrl(page,20)
        val client = OkHttpClient()
        val request = Request.Builder().url(path).get().build()
        client.newCall(request).enqueue(object : Callback {
            /**
             * 子线程调用
             */
            override fun onFailure(call: Call, e: IOException) {
                ThreadUtil.runOnMainthread(Runnable { refreshLayout.isRefreshing = false })
                myToast("获取数据失败")
            }

            /**
             * 子线程调用
             */
            override fun onResponse(call: Call, response: Response) {
                ThreadUtil.runOnMainthread(Runnable { refreshLayout.isRefreshing = false })
                myToast("获取数据成功")
                val result = response.body?.string()
                val gson = Gson()
                val list = gson.fromJson(result, HomeBean::class.java)
                ThreadUtil.runOnMainthread(Runnable {
                    refreshLayout.isRefreshing = false
                    adapter.loadMore(list.result)


                })
            }

        })
    }
}
