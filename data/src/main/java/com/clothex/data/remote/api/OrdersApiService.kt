package com.clothex.data.remote.api

import com.clothex.data.domain.model.SimpleResponse
import com.clothex.data.domain.model.body.OrderBody
import com.clothex.data.domain.model.order.MyOrder
import retrofit2.http.*

/**
 * Created by Mohamed Elshafey on 10/10/2020.
 */
interface OrdersApiService {
    @GET("my_orders")
    suspend fun getMyOrders(@Query("customer_id") customerId: String): List<MyOrder>

    @POST("make_order")
    suspend fun makeOrder(@Body body: OrderBody): SimpleResponse
}