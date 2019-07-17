package com.seven.kotlintest.adapter

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.seven.kotlintest.ui.fragment.NewsPagerFragment

class NewsPagerAdapter(val list:List<String>?, fm: FragmentManager?) : FragmentPagerAdapter(fm) {

    private val newsTitle: List<String> = listOf(
        "新闻", "娱乐", "体育", "财经", "军事", "科技", "手机", "数码", "时尚", "游戏", "教育", "健康", "旅游"
    )

    override fun getItem(p0: Int): Fragment {
        val fragment = NewsPagerFragment()
        val bundle = Bundle()
        bundle.putString("args",list?.get(p0))
        fragment.arguments = bundle
        return fragment
    }

    override fun getCount(): Int {
        return list?.size?:0
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return newsTitle[position]
    }
}