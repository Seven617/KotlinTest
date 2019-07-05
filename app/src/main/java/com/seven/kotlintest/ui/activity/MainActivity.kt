package com.seven.kotlintest.ui.activity


import android.support.v7.widget.Toolbar
import com.seven.kotlintest.R

import com.seven.kotlintest.base.BaseActivity
import com.seven.kotlintest.util.FragmentUtil
import com.seven.kotlintest.util.ToolBarManager
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.find

class MainActivity : BaseActivity(), ToolBarManager {
    private var mExitTime = 0L
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

    override fun onBackPressed() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            myToast("连按两下退出应用")
            //showToast("连按两下退出应用",Toast.LENGTH_SHORT) //此种方法调用也可以 有点可变参数的意思在里面
            mExitTime = System.currentTimeMillis()
        } else {
            super.onBackPressed()
        }
    }

}
