package com.clothex.data.domain.model.sign

data class SignupBody(
    val email: String,
    val password: String,
    val phone_number: String,
    val username: String
)