package com.clothex.data.remote.repository

import com.clothex.data.domain.model.home.HomeProduct
import com.clothex.data.domain.repository.home.IGetProductPageRepository
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
        color: String?,
        search: String?
    ): Flow<List<HomeProduct>?> =
        flow {
            emit(
                apiService.getProductPage(
                    page,
                    sort,
                    priceStart,
                    priceEnd,
                    size,
                    color,
                    search
                )
            )
        }
}