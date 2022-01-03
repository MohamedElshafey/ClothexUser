package com.clothex.data.domain.usecases.home

import com.clothex.data.domain.model.product.Product
import com.clothex.data.domain.repository.home.IGetProductDetailsRepository
import com.clothex.data.domain.usecases.BaseUseCase
import kotlinx.coroutines.flow.Flow

/**
 * Created by Mohamed Elshafey on 11/17/2020.
 */

typealias GetProductDetailsBaseUseCase = BaseUseCase<String, Flow<Product?>>

class GetProductDetailsUseCase(private val repository: IGetProductDetailsRepository) :
    GetProductDetailsBaseUseCase {
    override suspend fun invoke(params: String): Flow<Product?> =
        repository.getProductDetails(params)
}