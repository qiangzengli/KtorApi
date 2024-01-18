package com.alan.req

import kotlinx.serialization.Serializable


@Serializable
data class RegisterUserReq(
    val name: String = "",
    val password: String = "",
    val phone: String = "",
)
