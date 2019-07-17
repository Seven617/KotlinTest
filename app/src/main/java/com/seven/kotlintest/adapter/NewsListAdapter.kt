package com.seven.kotlintest.adapter

import android.content.Context
import com.seven.kotlintest.base.BaseListAdapter
import com.seven.kotlintest.model.NewsBean
import com.seven.kotlintest.widget.NewsItemView

class NewsListAdapter:BaseListAdapter<NewsBean,NewsItemView>() {
    override fun refreshItemView(itemView: NewsItemView, data: NewsBean) {
        itemView.setData(data)
    }

    override fun getItemView(context: Context?): NewsItemView {
        return NewsItemView(context)
    }
}