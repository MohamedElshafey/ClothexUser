package com.clothex.user.data

import com.clothex.shop.core.models.Item

/**
 * Created by Mohamed Elshafey on 14/11/2021.
 */
fun Item.oldTitle(): String {
    val products = department?.products
    var title = "${products?.title} ${products?.sub_product?.title}"
    products?.sub_product?.tags?.forEach {
        title += (" ${it.title}")
    }
    return title
}

fun Item.pictureUrl(): String {
    val mainImages = images
    if (!mainImages.isNullOrEmpty()) {
        return mainImages[0].source
    }
    colors?.forEach {
        if (!it.images.isNullOrEmpty()) {
            return it.images?.get(0)?.source!!
        }
    }
    return ""
}