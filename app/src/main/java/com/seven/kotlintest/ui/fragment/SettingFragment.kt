package com.seven.kotlintest.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.preference.Preference
import android.preference.PreferenceFragment
import android.preference.PreferenceScreen
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.seven.kotlintest.R
import com.seven.kotlintest.ui.activity.AboutActivity
import org.jetbrains.anko.toast


@Suppress("DEPRECATION")
class SettingFragment: PreferenceFragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        addPreferencesFromResource(R.xml.setting)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onPreferenceTreeClick(preferenceScreen: PreferenceScreen?, preference: Preference?): Boolean {
        val key = preference?.key
        if("about".equals(key)){
            toast("点击了关于")
            activity.startActivity(Intent(activity, AboutActivity::class.java))
        }
        return super.onPreferenceTreeClick(preferenceScreen, preference)
    }
}





//package com.seven.kotlintest.ui.fragment
//
//import android.content.Intent
//import android.os.Bundle
//import android.support.v7.preference.PreferenceFragmentCompat
//import com.seven.kotlintest.R
//import com.seven.kotlintest.ui.activity.AboutActivity
//
//
//class SettingFragment : PreferenceFragmentCompat() {
//    override fun onCreatePreferences(p0: Bundle?, p1: String?) {
//        addPreferencesFromResource(R.xml.setting)
//    }
//
//    override fun onPreferenceTreeClick(preference: android.support.v7.preference.Preference?): Boolean {
//        val key = preference?.key
//        if ("about".equals(key)) {
//            startActivity(Intent(activity, AboutActivity::class.java))
//        }
//        return super.onPreferenceTreeClick(preference)
//    }
//}