package com.clothex.data.remote.repository

import com.clothex.data.domain.model.product.Product
import com.clothex.data.domain.repository.home.IGetProductDetailsRepository
import com.clothex.data.remote.api.ProductApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by Mohamed Elshafey on 11/17/2020.
 */
class GetProductDetailsRepository(private val apiService: ProductApiService) :
    IGetProductDetailsRepository {
    override suspend fun getProductDetails(productId: String): Flow<Product?> =
        flow { emit(apiService.getProductDetails(productId)) }
}