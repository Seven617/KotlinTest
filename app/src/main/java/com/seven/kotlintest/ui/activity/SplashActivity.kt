package com.seven.kotlintest.ui.activity

import com.seven.kotlintest.R
import com.seven.kotlintest.base.BaseActivity
import kotlinx.android.synthetic.main.activity_splash.*


class SplashActivity : BaseActivity() {

    override fun initData() {
        super.initData()
        mParticleView.startAnim()
        mParticleView.setOnParticleAnimListener {
            //进入主界面
            startActivityAndFinish<MainActivity>()
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_splash
    }
}