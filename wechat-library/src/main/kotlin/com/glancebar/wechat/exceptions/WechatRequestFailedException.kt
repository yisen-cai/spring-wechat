package com.glancebar.wechat.exceptions

import com.glancebar.wechat.results.ErrorResult


/**
 *
 * @author Ethan Gary
 */
class WechatRequestFailedException(
    val errorResult: ErrorResult
) : RuntimeException(errorResult.toString())