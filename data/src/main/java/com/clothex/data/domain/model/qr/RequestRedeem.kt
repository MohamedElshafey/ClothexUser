package com.clothex.data.domain.model.qr

import com.google.gson.annotations.SerializedName

data class RequestRedeem(
    @SerializedName("qr_code")
    val qrCode: String
)