package com.seven.kotlintest.adapter

import android.content.Context
import com.seven.kotlintest.base.BaseListAdapter
import com.seven.kotlintest.model.HomeItemBean
import com.seven.kotlintest.widget.HomeItemView


class HomeAdapter : BaseListAdapter<HomeItemBean, HomeItemView>() {
    override fun refreshItemView(itemView: HomeItemView, data: HomeItemBean) {
        itemView.setData(data)
    }


    override fun getItemView(context: Context?): HomeItemView {
        return HomeItemView(context)
    }

}



