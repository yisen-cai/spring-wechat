package com.glancebar.wechat

import com.glancebar.wechat.results.AccessTokenResult
import com.glancebar.wechat.results.Code2SessionResult
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate


/**
 *
 * @author Ethan Gary
 * @date 2021/2/1
 */
class WechatApi(
    private var wechatConfig: WechatConfig
) {

    /**
     * https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/login/auth.code2Session.html
     */
    fun code2Session(jsCode: String): Code2SessionResult? {
        val restTemplate = RestTemplate()
        val url = code2SessionURL.format(
            wechatConfig[WechatConfigParams.MINI_PROGRAM_APP_ID],
            wechatConfig[WechatConfigParams.MINI_PROGRAM_APP_SECRET],
            jsCode
        )
        val responseEntity: ResponseEntity<Code2SessionResult> = restTemplate.exchange(
            url,
            HttpMethod.GET,
            HttpEntity.EMPTY,
            Code2SessionResult::class.java
        )
        return responseEntity.body
    }


    /**
     * https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/access-token/auth.getAccessToken.html
     */
    fun getAccessToken(): AccessTokenResult? {
        val restTemplate = RestTemplate()
        val url = getAccessTokenURL.format(
            wechatConfig[WechatConfigParams.MINI_PROGRAM_APP_ID],
            wechatConfig[WechatConfigParams.MINI_PROGRAM_APP_SECRET]
        )

        val responseEntity: ResponseEntity<AccessTokenResult> = restTemplate.exchange(
            url,
            HttpMethod.GET,
            HttpEntity.EMPTY,
            AccessTokenResult::class.java
        )
        return responseEntity.body
    }


    companion object {
        const val code2SessionURL =
            "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code"

        const val getAccessTokenURL =
            "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s"

    }

}