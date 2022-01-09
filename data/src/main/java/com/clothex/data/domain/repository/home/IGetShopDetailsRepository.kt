package com.clothex.data.domain.repository.home

import com.clothex.data.domain.model.shop.Shop
import kotlinx.coroutines.flow.Flow

/**
 * Created by Mohamed Elshafey on 10/10/2020.
 */
interface IGetShopDetailsRepository {
    suspend fun getShopDetails(shopId: String): Flow<Shop?>
}