package com.clothex.data.domain.usecases.product

import com.clothex.data.domain.model.body.ProductBody
import com.clothex.data.domain.model.home.HomeProduct
import com.clothex.data.domain.repository.home.IGetProductPageRepository
import com.clothex.data.domain.usecases.BaseUseCase
import kotlinx.coroutines.flow.Flow

/**
 * Created by Mohamed Elshafey on 11/17/2020.
 */

typealias GetProductPageBaseUseCase = BaseUseCase<ProductBody, Flow<List<HomeProduct>?>>

class GetProductPageUseCase(private val repository: IGetProductPageRepository) :
    GetProductPageBaseUseCase {
    override suspend fun invoke(params: ProductBody): Flow<List<HomeProduct>?> =
        repository.getProducts(
            page = params.page,
            sort = params.sort,
            priceStart = params.priceStart,
            priceEnd = params.priceEnd,
            size = params.size,
            color = params.color,
            search = params.search
        )
}