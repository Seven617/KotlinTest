package com.seven.kotlintest.ui.activity

import android.annotation.SuppressLint
import android.webkit.WebView
import android.webkit.WebViewClient
import com.seven.kotlintest.R
import com.seven.kotlintest.base.BaseActivity
import kotlinx.android.synthetic.main.activity_webview.*

@SuppressLint("Registered")
class WebViewActivity: BaseActivity() {

//    val toolbar by lazy { Toolbar }
    override fun getLayoutId(): Int {
        return R.layout.activity_webview
    }

    override fun initData() {
        super.initData()
        initToolBar()
        initView()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initView() {
        val bundle = intent.extras
        val path = bundle.getString("path")
        val webSet = webView.settings
        webSet.javaScriptEnabled = true
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return true
            }
        }
        webView.loadUrl(path)
    }

    private fun initToolBar() {
        
    }
}