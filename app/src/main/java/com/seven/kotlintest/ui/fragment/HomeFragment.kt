package com.seven.kotlintest.ui.fragment

import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.google.gson.Gson
import com.seven.kotlintest.R
import com.seven.kotlintest.adapter.HomeAdapter
import com.seven.kotlintest.base.BaseFragment
import com.seven.kotlintest.model.Data
import com.seven.kotlintest.model.HomeItemBean
import com.seven.kotlintest.util.ThreadUtil
import com.seven.kotlintest.util.URLProviderUtils
import kotlinx.android.synthetic.main.fragment_home.*
import okhttp3.*
import java.io.IOException

class HomeFragment : BaseFragment() {

    val adapter by lazy { HomeAdapter() }
    var pageNumber: Int = 0

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
                            loadMore(pageNumber)
                        }
                    }
                }
            }

        })
    }

    private fun loadDatas() {
        val path = URLProviderUtils.getHomeUrl(0)
        val client = OkHttpClient()
        val request = Request.Builder().url(path).get().build()
        client.newCall(request).enqueue(object : Callback {
            /**
             * 子线程调用
             */
            override fun onFailure(call: Call, e: IOException) {
                ThreadUtil.runOnMainthread(object : Runnable {
                    override fun run() {
                        refreshLayout.isRefreshing = false
                    }

                })
                myToast("获取数据失败")
            }

            /**
             * 子线程调用
             */
            override fun onResponse(call: Call, response: Response) {
                ThreadUtil.runOnMainthread(object : Runnable {
                    override fun run() {
                        refreshLayout.isRefreshing = false
                    }

                })
                myToast("获取数据成功")
                val result = response.body?.string()
                //println("获取数据成功" + result)
                val gson = Gson()
                val list = gson.fromJson(result, HomeItemBean::class.java)
                pageNumber = list.page
                ThreadUtil.runOnMainthread(Runnable { adapter.updateList(list.data) })
            }

        })
    }


    private fun loadMore(page: Int) {
        val path = URLProviderUtils.getHomeUrl(page)
        val client = OkHttpClient()
        val request = Request.Builder().url(path).get().build()
        client.newCall(request).enqueue(object : Callback {
            /**
             * 子线程调用
             */
            override fun onFailure(call: Call, e: IOException) {
                ThreadUtil.runOnMainthread(object : Runnable {
                    override fun run() {
                        refreshLayout.isRefreshing = false
                    }
                })
                myToast("获取数据失败")
            }

            /**
             * 子线程调用
             */
            override fun onResponse(call: Call, response: Response) {
                ThreadUtil.runOnMainthread(object : Runnable {
                    override fun run() {
                        refreshLayout.isRefreshing = false
                    }
                })
                myToast("获取数据成功")
                val result = response.body?.string().toString()
                val gson = Gson()
                val list = gson.fromJson(result, HomeItemBean::class.javaObjectType)
                ThreadUtil.runOnMainthread(object : Runnable {
                    override fun run() {
                        refreshLayout.isRefreshing = false
                        adapter.loadMore(list.data)
                        pageNumber = list.page
                        println("pageNumber是:$pageNumber")
                    }

                })
            }

        })
    }
}
