package com.seven.kotlintest.widget

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.bumptech.glide.Glide
import com.seven.kotlintest.R
import com.seven.kotlintest.model.HomeItemBean
import kotlinx.android.synthetic.main.item_home.view.*

class HomeItemView : RelativeLayout {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        View.inflate(context, R.layout.item_home, this)
    }

    @SuppressLint("SetTextI18n")
    fun setData(data: HomeItemBean) {
        title.text = data.title
        Glide.with(this).load(data.imgsrc).into(image1)
        time.text = data.ptime
        source.text = data.source
    }
}