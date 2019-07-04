package com.seven.kotlintest.adapter


interface OnItemClickListener {

    fun onItemClick(url: String)

    fun onImageClick(position:Int,path: List<String>)
}