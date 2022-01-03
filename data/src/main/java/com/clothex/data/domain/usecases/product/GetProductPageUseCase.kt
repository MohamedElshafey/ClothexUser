package com.clothex.data.domain.usecases.product

import com.clothex.data.domain.model.home.HomeProduct
import com.clothex.data.domain.repository.home.IGetProductPageRepository
import com.clothex.data.domain.usecases.BaseUseCase
import kotlinx.coroutines.flow.Flow

/**
 * Created by Mohamed Elshafey on 11/17/2020.
 */

typealias GetProductPageBaseUseCase = BaseUseCase<Int, Flow<List<HomeProduct>?>>

class GetProductPageUseCase(private val repository: IGetProductPageRepository) :
    GetProductPageBaseUseCase {
    override suspend fun invoke(params: Int): Flow<List<HomeProduct>?> =
        repository.getProducts(params)
}