package com.seven.kotlintest.util

import android.content.Intent
import android.support.v7.widget.Toolbar
import android.view.MenuItem

import com.seven.kotlintest.R
import com.seven.kotlintest.ui.activity.SettingActivity

interface ToolBarManager {

    val toolbar: Toolbar

    fun initMainToolBar() {
        toolbar.setTitle("Kotlin影音")
        toolbar.inflateMenu(R.menu.main)
        toolbar.setOnMenuItemClickListener {
            toolbar.context.startActivity(Intent(toolbar.context,SettingActivity::class.java))
            true
        }


//        toolbar.setOnMenuItemClickListener(object : Toolbar.OnMenuItemClickListener {
//            override fun onMenuItemClick(item: MenuItem?): Boolean {
//                when(item?.itemId){
//                    R.id.setting->{
//                        toolbar.context.startActivity(Intent(toolbar.context,SettingActivity::class.java))
//                    }
//                }
//
//
//                return true
//            }
//
//        })
    }


    fun initSettingToolBar(){
        toolbar.setTitle("设置")
    }
}