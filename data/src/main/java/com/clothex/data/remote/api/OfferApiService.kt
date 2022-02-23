package com.clothex.data.remote.api

import com.clothex.data.domain.model.offer.Offer
import retrofit2.http.GET

/**
 * Created by Mohamed Elshafey on 10/10/2020.
 */
interface OfferApiService {
    @GET("offers")
    suspend fun getOffers(): List<Offer>
}