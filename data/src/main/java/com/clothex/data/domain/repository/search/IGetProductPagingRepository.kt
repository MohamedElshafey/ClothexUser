package com.clothex.data.domain.repository.search

import androidx.paging.PagingData
import com.clothex.data.domain.model.home.HomeProduct
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

/**
 * Created by Mohamed Elshafey on 10/10/2020.
 */
interface IGetProductPagingRepository {
    suspend fun getProducts(
        page: Int,
        sort: String?,
        priceStart: Int?,
        priceEnd: Int?,
        size: String?,
        shopId: String?,
        color: String?,
        type: String?,
        department: String?,
        search: String?,
        coroutineScope: CoroutineScope
    ): Flow<PagingData<HomeProduct>>
}