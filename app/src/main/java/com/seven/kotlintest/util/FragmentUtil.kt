package com.seven.kotlintest.util

import com.seven.kotlintest.R
import com.seven.kotlintest.base.BaseFragment
import com.seven.kotlintest.ui.fragment.HomeFragment
import com.seven.kotlintest.ui.fragment.MvFragment
import com.seven.kotlintest.ui.fragment.VBangFragment
import com.seven.kotlintest.ui.fragment.YueDanFragment

class FragmentUtil private constructor(){
    val homeFragment by lazy { HomeFragment() }
    val mvFragment by lazy { MvFragment() }
    val vBangFragment by lazy { VBangFragment() }
    val yueDanFragment by lazy { YueDanFragment() }


    companion object{
        val fragmentUtil by lazy { FragmentUtil() }
    }

    fun getFragment(tabId:Int):BaseFragment?{
        when(tabId){
            R.id.tab_home->return  homeFragment
            R.id.tab_mv->return  mvFragment
            R.id.tab_vbang->return  vBangFragment
            R.id.tab_yuedan->return  yueDanFragment
        }
        return null
    }
}