package com.seven.kotlintest.model

import java.lang.reflect.Array

data class HomeItemBean(
    val data: List<Data>,
    val appCode: String,
    val dataType: String,
    val hasNext: Boolean,
    val page: Int,
    val retcode: String,
    val version: String
)

data class Data(
    val abstract: String,
    val article_genre: String,
    val behot_time: Number,
    val chinese_tag: String,
    val comments_count: Number,
    val label: Array,
    val composition: Int,
    val detail_play_effective_count: Int,
    val display_url: String,
    val gallary_image_count: Int,
    val go_detail_count: Int,
    val group_id: String,
    val group_source: Number,
    val has_gallery: Boolean,
    val has_video: Boolean,
    val image_list: List<Image>,
    val image_url: String,
    val is_top_article: Boolean,
    val item_id: String,
    val media_url: String,
    val middle_mode: Boolean,
    val more_mode: Boolean,
    val single_mode: Boolean,
    val source: String,
    val is_feed_ad: Boolean,
    val source_url: String,
    val tag: String,
    val tag_url: String,
    val title: String,
    val visibility: Int
)

data class Image(
    val height: Int,
    val uri: String,
    val url: String,
    val url_list: List<Url>,
    val width: Int
)

data class Url(
    val url: String
)