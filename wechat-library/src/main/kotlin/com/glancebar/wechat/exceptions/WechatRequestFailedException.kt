package com.glancebar.wechat.exceptions

import com.glancebar.wechat.results.ErrorResult


/**
 *
 * @author YISEN CAI
 */
class WechatRequestFailedException(
    val errorResult: ErrorResult
) : RuntimeException(errorResult.toString())