package com.clothex.user.data

/**
 * Created by Mohamed Elshafey on 20/11/2021.
 */
data class Product(
    val title: String,
    val imageUrl: String,
    val tag: String?,
    val tagColor: String?,
    val price: String,
    val oldPrice: String?
)
