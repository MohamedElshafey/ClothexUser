package com.clothex.data.remote.repository.search

import com.clothex.data.domain.model.home.HomeProduct
import com.clothex.data.domain.repository.search.IGetProductPageRepository
import com.clothex.data.remote.api.ProductApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by Mohamed Elshafey on 11/17/2020.
 */
class GetProductPageRepository(private val apiService: ProductApiService) :
    IGetProductPageRepository {

    override suspend fun getProducts(
        page: Int,
        sort: String?,
        priceStart: Int?,
        priceEnd: Int?,
        size: String?,
        shopId: String?,
        color: String?,
        search: String?
    ): Flow<List<HomeProduct>?> =
        flow {
            emit(
                apiService.getProductPage(
                    page = page,
                    sort = sort,
                    priceStart = priceStart,
                    priceEnd = priceEnd,
                    size = size,
                    shopId = shopId,
                    color = color,
                    search = search
                )
            )
        }
}