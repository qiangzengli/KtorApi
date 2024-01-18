package com.alan.resp

import kotlinx.serialization.Serializable


@Serializable
data class UserInfoResp(
    val phone: String,
    val name: String,
    val password: String,
)