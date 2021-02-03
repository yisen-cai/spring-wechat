package com.glancebar.wechat

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.glancebar.wechat.exceptions.WechatRequestFailedException
import com.glancebar.wechat.results.AccessTokenResult
import com.glancebar.wechat.results.Code2SessionResult
import com.glancebar.wechat.results.ErrorResult
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.web.client.RestTemplate

/**
 * Wechat MiniProgram server side API.
 * @author Ethan Gary
 */
class WechatMiniProgramApi(
    private var wechatConfig: WechatConfig
) {

    /**
     * https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/login/auth.code2Session.html
     */
    fun code2Session(jsCode: String): Code2SessionResult {
        val url = code2SessionURL.format(
            wechatConfig[WechatConfigParams.MINI_PROGRAM_APP_ID],
            wechatConfig[WechatConfigParams.MINI_PROGRAM_APP_SECRET],
            jsCode
        )

        val mapper = jacksonObjectMapper()
        return mapper.readValue(sendRequest(url), Code2SessionResult::class.java)
    }


    /**
     * https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/access-token/auth.getAccessToken.html
     */
    fun getAccessToken(): AccessTokenResult {
        val url = getAccessTokenURL.format(
            wechatConfig[WechatConfigParams.MINI_PROGRAM_APP_ID],
            wechatConfig[WechatConfigParams.MINI_PROGRAM_APP_SECRET]
        )
        val mapper = jacksonObjectMapper()
        return mapper.readValue(sendRequest(url), AccessTokenResult::class.java)
    }


    private fun sendRequest(
        url: String
    ): String {
        val restTemplate = RestTemplate()
        val bodyString = restTemplate.exchange(
            url,
            HttpMethod.GET,
            HttpEntity.EMPTY,
            String::class.java
        ).body!!

        // handle all kinds error
        if (bodyString.contains("errcode")) {
            val mapper = jacksonObjectMapper()
            val errorResult = mapper.readValue(bodyString, ErrorResult::class.java)
            val exception = WechatRequestFailedException(errorResult)
            throw exception
        }

        return bodyString
    }

    companion object {

        const val code2SessionURL =
            "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code"

        const val getAccessTokenURL =
            "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s"

    }
}