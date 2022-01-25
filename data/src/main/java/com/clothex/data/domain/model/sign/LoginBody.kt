package com.clothex.data.domain.model.sign

import com.google.gson.annotations.SerializedName

data class LoginBody(
    val password: String,
    @SerializedName("phone_number")
    val phoneNumber: String
)