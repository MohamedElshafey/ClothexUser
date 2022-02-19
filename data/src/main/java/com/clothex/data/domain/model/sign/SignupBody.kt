package com.clothex.data.domain.model.sign

data class SignupBody(
    val email: String,
    val password: String? = null,
    val phone_number: String? = null,
    val username: String,
    val googleToken: String? = null,
    val facebookToken: String? = null
)