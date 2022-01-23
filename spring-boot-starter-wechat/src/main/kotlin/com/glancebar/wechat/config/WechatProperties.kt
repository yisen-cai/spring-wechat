package com.glancebar.wechat.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

/**
 * Wechat config properties.
 * @author YISEN CAI
 */
@ConstructorBinding
@ConfigurationProperties(prefix = "wechat")
data class WechatProperties(
    val miniProgram: MiniProgram
) {

    data class MiniProgram(
        val appId: String,
        val appSecret: String
    )

}
