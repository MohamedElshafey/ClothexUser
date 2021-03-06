package com.clothex.data.domain.model.body

import kotlinx.coroutines.CoroutineScope

data class ProductBody(
    val page: Int,
    val sort: String? = null,
    val priceStart: Int? = null,
    val priceEnd: Int? = null,
    val size: String? = null,
    val shopId: String? = null,
    val color: String? = null,
    val department: String? = null,
    val type: String? = null,
    val search: String? = null,
    val coroutineScope: CoroutineScope
)
