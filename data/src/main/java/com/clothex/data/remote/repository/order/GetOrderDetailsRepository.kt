package com.clothex.data.remote.repository.order

import com.clothex.data.domain.model.order.MyOrder
import com.clothex.data.domain.repository.order.IGetOrderDetailsRepository
import com.clothex.data.remote.api.OrdersApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by Mohamed Elshafey on 11/17/2020.
 */
class GetOrderDetailsRepository(private val apiService: OrdersApiService) :
    IGetOrderDetailsRepository {
    override suspend fun getOrderDetails(orderId: String): Flow<MyOrder> = flow {
        apiService.getOrderDetails(orderId)
    }
}