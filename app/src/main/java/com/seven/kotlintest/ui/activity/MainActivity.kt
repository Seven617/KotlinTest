package com.seven.kotlintest.ui.activity


import android.support.v7.widget.Toolbar
import com.seven.kotlintest.R

import com.seven.kotlintest.base.BaseActivity
import com.seven.kotlintest.util.FragmentUtil
import com.seven.kotlintest.util.ToolBarManager
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.find

class MainActivity : BaseActivity(), ToolBarManager {
    /**
     * 惰性加载
     */
    override val toolbar: Toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initData() {
        super.initData()
        initMainToolBar()
    }

    override fun initListener() {
        super.initListener()
        bottomBar.setOnTabSelectListener {
            val transcation = supportFragmentManager.beginTransaction()
            FragmentUtil.fragmentUtil.getFragment(it)?.let { it1 ->
                transcation.replace(R.id.container,
                    it1, it.toString())
            }
            transcation.commit()
        }
    }

}
