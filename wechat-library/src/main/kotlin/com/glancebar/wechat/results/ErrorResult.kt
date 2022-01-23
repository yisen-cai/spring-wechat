package com.glancebar.wechat.results

/**
 * Request failed result.
 */
data class ErrorResult(
    val errcode: Int,
    val errmsg: String
)
