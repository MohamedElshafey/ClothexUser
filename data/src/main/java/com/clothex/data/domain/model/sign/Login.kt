package com.clothex.data.domain.model.sign

import com.clothex.data.domain.model.user.User

data class Login(
    val token: String,
    val user: User
)