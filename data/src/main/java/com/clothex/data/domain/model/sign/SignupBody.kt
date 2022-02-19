package com.clothex.data.domain.model.sign

data class SignupBody(
    val email: String,
    val password: String? = null,
    val phone_number: String? = null,
    val username: String,
    val token: String? = null
)