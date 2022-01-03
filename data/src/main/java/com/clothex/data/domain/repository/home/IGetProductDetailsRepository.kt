package com.clothex.data.domain.repository.home

import com.clothex.data.domain.model.product.Product
import kotlinx.coroutines.flow.Flow

/**
 * Created by Mohamed Elshafey on 10/10/2020.
 */
interface IGetProductDetailsRepository {
    suspend fun getProductDetails(productId: String): Flow<Product?>
}