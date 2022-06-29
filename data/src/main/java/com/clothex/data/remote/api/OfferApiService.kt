package com.clothex.data.remote.api

import com.clothex.data.domain.model.offer.Offer
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Mohamed Elshafey on 10/10/2020.
 */
interface OfferApiService {
    @GET("offers")
    suspend fun getOffers(): List<Offer>

    @GET("shop-offers")
    suspend fun getShopOffers(@Query("shopId") shopId: String): List<Offer>
}