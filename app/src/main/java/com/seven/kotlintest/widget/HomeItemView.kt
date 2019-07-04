package com.seven.kotlintest.widget

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.bumptech.glide.Glide
import com.seven.kotlintest.R
import com.seven.kotlintest.model.Data
import kotlinx.android.synthetic.main.item_home.view.*

class HomeItemView : RelativeLayout {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        View.inflate(context, R.layout.item_home, this)
    }

    @SuppressLint("SetTextI18n")
    fun setData(data: Data) {
        title.text = data.title
        val imgList = data.image_list
        if (imgList.isNotEmpty()) {
            imgLayout.visibility = (View.VISIBLE)
            remark.visibility = (View.GONE)
            Glide.with(this).load(imgList[0].url_list[0].url).into(image1)
            Glide.with(this).load(imgList[1].url_list[1].url).into(image2)
            Glide.with(this).load(imgList[2].url_list[2].url).into(image3)
        } else {
            imgLayout.visibility = (View.GONE)
            remark.visibility = (View.VISIBLE)
            remark.text = "\t\t\t\t"+data.abstract
            Glide.with(this).load("https:" + data.image_url).into(image1)
        }


    }
}