package com.glancebar.wechat

import com.glancebar.wechat.exceptions.WechatRequestFailedException
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
open class WechatMiniProgramApiTest {

    private lateinit var wechatMiniProgramApi: WechatMiniProgramApi

    @BeforeAll
    fun setUp() {
        val wechatConfig = WechatConfig()
        wechatConfig[WechatConfigParams.MINI_PROGRAM_APP_ID] = "wxef1c59645f9a2569"
        wechatConfig[WechatConfigParams.MINI_PROGRAM_APP_SECRET] = "be5c2c31927934d2d2fcc7ef14b95691"
        wechatMiniProgramApi = WechatMiniProgramApi(wechatConfig)
    }

//    @Test
    fun code2Session() {
        val code2SessionResult = wechatMiniProgramApi.code2Session("071o7u000tq36L1fKX200J3xtk0o7u0y")
        println(code2SessionResult)
        assertNotNull(code2SessionResult)
    }

    @Test
    fun getAccessToken() {
        val accessTokenResult = wechatMiniProgramApi.getAccessToken()
        println(accessTokenResult)
        assertNotNull(accessTokenResult)
    }

    @Test
    internal fun `code2Session with Exception`() {
        assertThrows(WechatRequestFailedException::class.java) {
            wechatMiniProgramApi.code2Session("")
        }
    }
}