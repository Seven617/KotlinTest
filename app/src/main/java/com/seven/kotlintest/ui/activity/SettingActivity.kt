package com.seven.kotlintest.ui.activity

import android.preference.PreferenceManager
import android.support.v7.widget.Toolbar
import com.seven.kotlintest.R
import com.seven.kotlintest.base.BaseActivity
import com.seven.kotlintest.util.ToolBarManager
import org.jetbrains.anko.find

class SettingActivity : BaseActivity(), ToolBarManager {
    override fun getLayoutId(): Int {
        return R.layout.activity_setting
    }

    override val toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    override fun initData() {
        super.initData()
        initSettingToolBar()
        val sp = PreferenceManager.getDefaultSharedPreferences(this)
        val push = sp.getBoolean("push",false)
        println("push=$push")
    }
}