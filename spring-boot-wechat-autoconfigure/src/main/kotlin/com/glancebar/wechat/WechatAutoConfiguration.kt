package com.glancebar.wechat

import com.glancebar.wechat.config.WechatProperties
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


/**
 * Auto config properties
 * @author Ethan Gary
 * @date 2021/2/1
 */
@Configuration
@ConditionalOnClass(WechatApi::class)
@EnableConfigurationProperties(value = [WechatProperties::class])
open class WechatAutoConfiguration(
    private val wechatProperties: WechatProperties
) {


    @Bean
    @ConditionalOnMissingBean
    open fun wechatConfig(): WechatConfig {
        val config = WechatConfig()
        config[WechatConfigParams.MINI_PROGRAM_APP_ID] = wechatProperties.miniProgram.appId
        config[WechatConfigParams.MINI_PROGRAM_APP_SECRET] = wechatProperties.miniProgram.appSecret
        return config
    }

    @Bean
    @ConditionalOnMissingBean
    open fun wechatApi(wechatConfig: WechatConfig): WechatApi {
        return WechatApi(wechatConfig)
    }
}