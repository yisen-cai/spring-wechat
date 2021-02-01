package com.glancebar.wechat.exceptions

import com.glancebar.wechat.results.ErrorResult


/**
 *
 * @author Ethan Gary
 * @date 2021/2/1
 */
class WechatRequestFailedException(
    val errorResult: ErrorResult
) : RuntimeException(errorResult.toString())