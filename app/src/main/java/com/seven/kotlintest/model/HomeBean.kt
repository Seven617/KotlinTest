package com.seven.kotlintest.model

data class HomeBean(
    val code: Int,
    val message: String,
    val result: List<Data>
)

data class Data(
    val image: String,
    val passtime: String,
    val path: String,
    val title: String
)