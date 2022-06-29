package com.clothex.data.remote.repository.offer

import com.clothex.data.domain.model.offer.Offer
import com.clothex.data.domain.repository.offer.IGetShopOffersRepository
import com.clothex.data.remote.api.OfferApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by Mohamed Elshafey on 11/17/2020.
 */
class GetShopOffersRepository(private val apiService: OfferApiService) : IGetShopOffersRepository {
    override suspend fun getShopOffers(shopId: String): Flow<List<Offer>> =
        flow { emit(apiService.getShopOffers(shopId)) }
}