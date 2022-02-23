package com.clothex.data.domain.repository.offer

import com.clothex.data.domain.model.offer.Offer
import kotlinx.coroutines.flow.Flow

/**
 * Created by Mohamed Elshafey on 10/10/2020.
 */
interface IGetOffersRepository {
    suspend fun getOffers(): Flow<List<Offer>>
}