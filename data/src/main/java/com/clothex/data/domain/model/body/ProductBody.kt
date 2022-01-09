package com.clothex.data.domain.model.body

data class ProductBody(
    val page: Int,
    val sort: String? = null,
    val priceStart: Int? = null,
    val priceEnd: Int? = null,
    val size: String? = null,
    val shopId: String? = null,
    val color: String? = null,
    val search: String? = null
)
