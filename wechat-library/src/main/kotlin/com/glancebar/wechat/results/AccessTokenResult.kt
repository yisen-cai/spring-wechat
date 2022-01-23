package com.glancebar.wechat.results


/**
 * 访问token接口反序列化对象
 *
 * @author YISEN CAI
 */
data class AccessTokenResult(
    val access_token: String,
    val expires_in: Int
) {
}