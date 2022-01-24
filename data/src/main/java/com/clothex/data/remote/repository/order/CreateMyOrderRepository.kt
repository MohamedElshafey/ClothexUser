package com.clothex.data.remote.repository.order

import com.clothex.data.domain.model.SimpleResponse
import com.clothex.data.domain.model.body.OrderBody
import com.clothex.data.domain.repository.order.ICreateMyOrderRepository
import com.clothex.data.remote.api.OrdersApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by Mohamed Elshafey on 11/17/2020.
 */
class CreateMyOrderRepository(private val apiService: OrdersApiService) : ICreateMyOrderRepository {
    override suspend fun createMyOrder(orderBody: OrderBody): Flow<SimpleResponse?> =
        flow { emit(apiService.makeOrder(orderBody)) }
}