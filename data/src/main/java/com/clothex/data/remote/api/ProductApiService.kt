package com.clothex.data.remote.api

import com.clothex.data.domain.model.home.HomeProduct
import com.clothex.data.domain.model.product.Product
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Mohamed Elshafey on 10/10/2020.
 */
interface ProductApiService {
    @GET("product-details?")
    suspend fun getProductDetails(@Query("product_id") productId: String): Product

    @GET("product/pages")
    suspend fun getProductPage(
        @Query("page") page: Int,
        @Query("sort") sort: String?,
        @Query("priceStart") priceStart: Int?,
        @Query("priceEnd") priceEnd: Int?,
        @Query("shopId") shopId: String?,
        @Query("size") size: String?,
        @Query("color") color: String?,
        @Query("search") search: String?
    ): List<HomeProduct>

}