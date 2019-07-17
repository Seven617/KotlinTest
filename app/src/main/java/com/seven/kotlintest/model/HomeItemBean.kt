package com.seven.kotlintest.model

data class HomeItemBean(
    var commentCount: Int = 0,
    var digest: String? = null,
    var docid: String? = null,
    var hasImg: Int = 0,
    var imgsrc: String? = null,
    var imgsrc3gtype: String? = null,
    var liveInfo: String? = null,
    var priority: Int = 0,
    var ptime: String? = null,
    var source: String? = null,
    var stitle: String? = null,
    var title: String? = null,
    var url: String? = null
)
