package com.glancebar.wechat.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "com.glancebar.wechat")
data class WechatProperties(
    val miniProgram: MiniProgram
) {

    data class MiniProgram(
        val appId: String,
        val appSecret: String
    )

}
