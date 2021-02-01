package com.glancebar.wechat.results


/**
 *
 * @author Ethan Gary
 * @date 2021/2/1
 */
data class AccessTokenResult(
    val access_token: String,
    val expires_in: Int
) {
}