package com.glancebar.wechat.results


/**
 *
 * @author Ethan Gary
 */
data class AccessTokenResult(
    val access_token: String,
    val expires_in: Int
) {
}