package com.clothex.data.domain.usecases.shop

import com.clothex.data.domain.model.shop.Shop
import com.clothex.data.domain.repository.home.IGetShopDetailsRepository
import com.clothex.data.domain.usecases.BaseUseCase
import kotlinx.coroutines.flow.Flow

/**
 * Created by Mohamed Elshafey on 11/17/2020.
 */

typealias GetShopDetailsBaseUseCase = BaseUseCase<String, Flow<Shop?>>

class GetShopDetailsUseCase(private val repository: IGetShopDetailsRepository) :
    GetShopDetailsBaseUseCase {
    override suspend fun invoke(params: String): Flow<Shop?> =
        repository.getShopDetails(params)
}