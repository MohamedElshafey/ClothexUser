package com.clothex.data.domain.model.product_page

import com.clothex.data.domain.model.home.HomeProduct

data class ProductPage(
    val docs: List<HomeProduct>,
    val hasNextPage: Boolean,
    val hasPrevPage: Boolean,
    val limit: Int,
    val nextPage: Int?,
    val page: Int,
    val pagingCounter: Int,
    val prevPage: Int?,
    val totalDocs: Int,
    val totalPages: Int
)