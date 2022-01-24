package com.clothex.data.domain.usecases.shop

import com.clothex.data.domain.model.body.ShopBody
import com.clothex.data.domain.model.home.HomeShop
import com.clothex.data.domain.repository.search.IGetShopPageRepository
import com.clothex.data.domain.usecases.BaseUseCase
import kotlinx.coroutines.flow.Flow

/**
 * Created by Mohamed Elshafey on 11/17/2020.
 */

typealias GetShopPageBaseUseCase = BaseUseCase<ShopBody, Flow<List<HomeShop>?>>

class GetShopPageUseCase(private val repository: IGetShopPageRepository) :
    GetShopPageBaseUseCase {
    override suspend fun invoke(params: ShopBody): Flow<List<HomeShop>?> =
        repository.getShops(page = params.page, search = params.search)
}