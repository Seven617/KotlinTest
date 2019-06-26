package com.seven.kotlintest.ui.activity

import android.support.v4.view.ViewCompat
import android.support.v4.view.ViewPropertyAnimatorListener
import android.view.View
import com.seven.kotlintest.R
import com.seven.kotlintest.base.BaseActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity: BaseActivity(), ViewPropertyAnimatorListener {

    override fun initData() {
        super.initData()
        ViewCompat.animate(imageView).scaleX(1.0f).scaleY(1.0f).setListener(this).setDuration(2000)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_splash
    }

    override fun onAnimationEnd(p0: View?) {
        //进入主界面
        startActivityAndFinish<MainActivity>()
    }

    override fun onAnimationCancel(p0: View?) {

    }

    override fun onAnimationStart(p0: View?) {

    }


}