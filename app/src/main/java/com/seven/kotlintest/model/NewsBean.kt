package com.seven.kotlintest.model

data class NewsBean(
    var commentCount: Int = 0,
    var digest: String? = null,
    var docid: String? = null,
    var hasImg: Int = 0,
    var imgsrc: String? = null,
    var imgsrc3gtype: String? = null,
    var liveInfo: Any? = null,
    var priority: Int = 0,
    var ptime: String? = null,
    var source: String? = null,
    var stitle: String? = null,
    var title: String? = null,
    var url: String? = null,
    val imgextra: List<Imgextra>? = null,
    val modelmode: String? = null,
    val photosetID: String? = null,
    val skipType: String? = null,
    val skipURL: String? = null

)

data class Imgextra(
    val imgsrc: String
)

