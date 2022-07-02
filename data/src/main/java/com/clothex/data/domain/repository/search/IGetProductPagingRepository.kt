package com.clothex.data.domain.repository.search

import androidx.paging.PagingSource
import com.clothex.data.domain.model.home.HomeProduct

/**
 * Created by Mohamed Elshafey on 10/10/2020.
 */
abstract class IGetProductPagingRepository : PagingSource<Int, HomeProduct>() {
    abstract suspend fun getProducts(
        page: Int,
        sort: String?,
        priceStart: Int?,
        priceEnd: Int?,
        size: String?,
        shopId: String?,
        color: String?,
        type: String?,
        department: String?,
        search: String?
    )
}