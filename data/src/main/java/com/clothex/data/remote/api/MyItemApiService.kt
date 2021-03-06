package com.clothex.data.remote.api

import com.clothex.data.domain.model.SimpleResponse
import com.clothex.data.domain.model.body.MyItemBody
import com.clothex.data.domain.model.my_item.MyItem
import retrofit2.http.*

/**
 * Created by Mohamed Elshafey on 10/10/2020.
 */
interface MyItemApiService {
    @POST("create_my_item")
    suspend fun createMyItem(@Body myItemBody: MyItemBody): MyItem?

    @DELETE("my_item/{id}")
    suspend fun deleteMyItem(@Path("id") id: String): SimpleResponse?

    @DELETE("my_items")
    suspend fun deleteMyItems(@Query("idsArray[]") id: List<String>): SimpleResponse?

    @GET("my_items")
    suspend fun getMyItems(): List<MyItem>
}