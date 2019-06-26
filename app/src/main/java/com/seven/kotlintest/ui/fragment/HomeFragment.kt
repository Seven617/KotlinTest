package com.seven.kotlintest.ui.fragment

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.seven.kotlintest.R
import com.seven.kotlintest.adapter.HomeAdapter
import com.seven.kotlintest.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment:BaseFragment() {
    override fun initView(): View? {
        return View.inflate(context, R.layout.fragment_home,null)
    }

    override fun initListener(){
        recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = HomeAdapter()
        recyclerView.adapter = adapter
    }
}