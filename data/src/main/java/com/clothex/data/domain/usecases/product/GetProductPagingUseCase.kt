package com.clothex.data.domain.usecases.product

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.clothex.data.domain.model.body.ProductBody
import com.clothex.data.domain.model.home.HomeProduct
import com.clothex.data.domain.repository.search.IGetProductPagingRepository
import com.clothex.data.domain.usecases.BaseUseCase
import kotlinx.coroutines.flow.Flow

/**
 * Created by Mohamed Elshafey on 11/17/2020.
 */

typealias GetProductPagingBaseUseCase = BaseUseCase<ProductBody, Flow<PagingData<HomeProduct>>>

class GetProductPagingUseCase(private val repository: IGetProductPagingRepository) :
    GetProductPagingBaseUseCase {
    override suspend fun invoke(params: ProductBody): Flow<PagingData<HomeProduct>> {
        repository.getProducts(
            page = params.page,
            sort = params.sort,
            priceStart = params.priceStart,
            priceEnd = params.priceEnd,
            size = params.size,
            shopId = params.shopId,
            color = params.color,
            department = params.department,
            type = params.type,
            search = params.search
        )
        return Pager(PagingConfig(pageSize = 20)) { repository }.flow
    }

}