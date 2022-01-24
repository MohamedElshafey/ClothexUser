package com.clothex.data.domain.repository.search

import com.clothex.data.domain.model.home.HomeShop
import kotlinx.coroutines.flow.Flow

/**
 * Created by Mohamed Elshafey on 10/10/2020.
 */
interface IGetShopPageRepository {
    suspend fun getShops(
        page: Int,
        search: String?
    ): Flow<List<HomeShop>?>
}