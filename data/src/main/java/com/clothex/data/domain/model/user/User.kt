package com.clothex.data.domain.model.user

import com.google.gson.annotations.SerializedName

data class User(
    val username: String,
    val email: String,
    @SerializedName("phone_number")
    val phoneNumber: String
)