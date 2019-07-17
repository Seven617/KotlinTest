package com.seven.kotlintest.ui.fragment

import android.support.design.widget.TabLayout
import android.view.View
import com.seven.kotlintest.R
import com.seven.kotlintest.adapter.NewsPagerAdapter
import com.seven.kotlintest.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_news.*


class NewsFragment : BaseFragment() {
    private val newsCode: List<String> = listOf(
        "BBM54PGAwangning",
        "BA10TA81wangning",
        "BA8E6OEOwangning",
        "BA8EE5GMwangning",
        "BAI67OGGwangning",
        "BA8D4A3Rwangning",
        "BAI6I0O5wangning",
        "BAI6JOD9wangning",
        "BA8F6ICNwangning",
        "BAI6RHDKwangning",
        "BA8FF5PRwangning",
        "BDC4QSV3wangning",
        "BEO4GINLwangning"
    )

    override fun initView(): View? {
        return View.inflate(context, R.layout.fragment_news, null)
    }

    override fun initData() {
        super.initData()
        val adapter = NewsPagerAdapter(newsCode, childFragmentManager)
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
        tabLayout.addOnTabSelectedListener(tbcl)
    }

    private var tbcl: TabLayout.OnTabSelectedListener = object : TabLayout.OnTabSelectedListener {
        override fun onTabSelected(tab: TabLayout.Tab) {
            //TabLayout里的TabItem被选中的时候触发
            // 默认切换的时候，会有一个过渡动画，设为false后，取消动画，直接显示
            viewPager.setCurrentItem(tab.position, false)
        }

        override fun onTabUnselected(tab: TabLayout.Tab) {

        }

        override fun onTabReselected(tab: TabLayout.Tab) {

        }
    }


}