package com.clothex.data.remote.api

import com.clothex.data.domain.model.shop.Shop
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Mohamed Elshafey on 10/10/2020.
 */
interface ShopApiService {
    @GET("shop-details")
    suspend fun getShopDetails(@Query("shop_id") shopId: String): Shop
}