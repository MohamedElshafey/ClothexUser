package com.clothex.data.domain.model.sign

data class LoginBody(
    val password: String? = null,
    val email: String,
    val token: String? = null
)