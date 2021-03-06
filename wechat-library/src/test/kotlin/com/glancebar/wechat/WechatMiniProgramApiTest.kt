package com.glancebar.wechat

import com.glancebar.wechat.exceptions.WechatRequestFailedException
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Disabled("Enable when there have APP_ID and APP_SECRET environment variable.")
open class WechatMiniProgramApiTest {

    private lateinit var wechatMiniProgramApi: WechatMiniProgramApi

    @BeforeAll
    fun setUp() {
        val wechatConfig = WechatConfig()
        println(System.getenv())
        // load from system env
        wechatConfig[WechatConfigParams.MINI_PROGRAM_APP_ID] = System.getenv("APP_ID")
        wechatConfig[WechatConfigParams.MINI_PROGRAM_APP_SECRET] = System.getenv("APP_SECRET")
//        wechatConfig[WechatConfigParams.MINI_PROGRAM_APP_ID] = System.getProperty("appId")
//        wechatConfig[WechatConfigParams.MINI_PROGRAM_APP_SECRET] = System.getProperty("appSecret")
        wechatMiniProgramApi = WechatMiniProgramApi(wechatConfig)
    }

    /**
     * this test need to using wechat developer tools generate login jsCode
     */
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