package com.glancebar.wechat.results


/**
 * Code2Session接口返回值序列化对象
 *
 * @author YISEN CAI
 */
data class Code2SessionResult(

    val session_key: String,
    /**
     * 用户openid
     */
    val openid: String
)