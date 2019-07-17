package com.seven.kotlintest.ui.activity

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.LinearLayout
import com.just.agentweb.AgentWeb
import com.just.agentweb.DefaultWebClient
import com.seven.kotlintest.R
import com.seven.kotlintest.base.BaseActivity
import android.graphics.Bitmap
import android.view.KeyEvent
import android.webkit.WebResourceRequest
import kotlinx.android.synthetic.main.activity_webview.*


@SuppressLint("Registered")
class WebViewActivity : BaseActivity() {

    private var mAgentWeb: AgentWeb? = null
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

        //初始化AgentWeb对象
        mAgentWeb = AgentWeb.with(this)
            .setAgentWebParent(
                mLinearLayout, LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
                ) as ViewGroup.LayoutParams
            ).useDefaultIndicator()
            .setWebChromeClient(mWebChromeClient)
            .setWebViewClient(mWebViewClient)//注意这里！！！！！！！！
            .setMainFrameErrorView(R.layout.agentweb_error_page, -1)
            .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
            .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.ASK)//打开其他应用时，弹窗咨询用户是否前往其他应用
            .interceptUnkownUrl() //拦截找不到相关页面的Scheme
            .createAgentWeb()
            .ready()
            .go(path)
    }

    private val mWebViewClient = object : com.just.agentweb.WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
            return super.shouldOverrideUrlLoading(view, request)
        }

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            //do you  work
            super.onPageStarted(view, url, favicon)
        }
    }
    private val mWebChromeClient = object : com.just.agentweb.WebChromeClient() {
        override fun onReceivedTitle(view: WebView, title: String) {
            super.onReceivedTitle(view, title)

        }
    }


    private fun initToolBar() {

    }


    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {

        return if (mAgentWeb!!.handleKeyEvent(keyCode, event)) {
            true
        } else super.onKeyDown(keyCode, event)
    }

    override fun onPause() {
        mAgentWeb!!.webLifeCycle.onPause()
        super.onPause()

    }


    override fun onResume() {
        mAgentWeb!!.webLifeCycle.onResume()
        super.onResume()
    }

    override fun onDestroy() {
        mAgentWeb!!.webLifeCycle.onDestroy()
        super.onDestroy()
    }
}


