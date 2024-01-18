package com.alan.resp

import kotlinx.serialization.Serializable

/**
 * 返回结果实体
 */
@Serializable
data class BaseResult<T>  constructor(
    var status: Int,
    var msg: String,
    var code: String,
    var data: T? = null,
    var success: Boolean,
)

fun <T> respOk(
    msg: String = "OK",
    code: String = "0",
    data: T? = null,
) = BaseResult(
    status = 1,
    success = true,
    code = code,
    msg = msg,
    data = data,
)

fun respFail(
    msg: String,
    code: String = "-1",
) = BaseResult(
    status = 0,
    success = false,
    code = code,
    msg = msg,
    data = null
)
