package com.clothex.data.domain.usecases.offer

import com.clothex.data.domain.model.offer.Offer
import com.clothex.data.domain.repository.offer.IGetShopOffersRepository
import com.clothex.data.domain.usecases.BaseUseCase
import kotlinx.coroutines.flow.Flow

/**
 * Created by Mohamed Elshafey on 11/17/2020.
 */

typealias GetShopOffersBaseUseCase = BaseUseCase<String, Flow<List<Offer>>>

class GetShopOffersUseCase(private val repository: IGetShopOffersRepository) :
    GetShopOffersBaseUseCase {
    override suspend fun invoke(params: String): Flow<List<Offer>> =
        repository.getShopOffers(params)
}