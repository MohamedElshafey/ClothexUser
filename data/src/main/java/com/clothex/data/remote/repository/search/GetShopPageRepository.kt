package com.clothex.data.remote.repository.search

import com.clothex.data.domain.model.home.HomeShop
import com.clothex.data.domain.repository.search.IGetShopPageRepository
import com.clothex.data.remote.api.ShopApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by Mohamed Elshafey on 11/17/2020.
 */
class GetShopPageRepository(private val apiService: ShopApiService) : IGetShopPageRepository {

    override suspend fun getShops(
        page: Int,
        search: String?
    ): Flow<List<HomeShop>?> =
        flow {
            emit(apiService.getShopPage(page = page, search = search))
        }
}