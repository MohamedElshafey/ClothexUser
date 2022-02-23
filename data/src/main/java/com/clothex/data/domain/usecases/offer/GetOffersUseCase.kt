package com.clothex.data.domain.usecases.offer

import com.clothex.data.domain.model.offer.Offer
import com.clothex.data.domain.repository.offer.IGetOffersRepository
import com.clothex.data.domain.usecases.BaseUseCase
import kotlinx.coroutines.flow.Flow

/**
 * Created by Mohamed Elshafey on 11/17/2020.
 */

typealias GetOffersBaseUseCase = BaseUseCase<Unit, Flow<List<Offer>>>

class GetOffersUseCase(private val repository: IGetOffersRepository) : GetOffersBaseUseCase {
    override suspend fun invoke(params: Unit): Flow<List<Offer>> = repository.getOffers()
}