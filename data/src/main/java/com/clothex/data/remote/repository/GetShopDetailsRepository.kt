package com.clothex.data.remote.repository

import com.clothex.data.domain.model.shop.Shop
import com.clothex.data.domain.repository.home.IGetShopDetailsRepository
import com.clothex.data.remote.api.ShopApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by Mohamed Elshafey on 11/17/2020.
 */
class GetShopDetailsRepository(private val apiService: ShopApiService) :
    IGetShopDetailsRepository {
    override suspend fun getShopDetails(shopId: String): Flow<Shop?> =
        flow { emit(apiService.getShopDetails(shopId)) }
}