package com.clothex.user.utils

import com.clothex.user.BuildConfig


fun getBaseDeeplink(): String {
    val baseUrl = BuildConfig.BASE_URL
    return "${baseUrl}deeplink?link="
}

fun getProductDetailsDeeplink(productId: String): String {
    return getBaseDeeplink() + "clothex://app.user/product/$productId"
}