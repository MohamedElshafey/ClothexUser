package com.clothex.data.remote.repository.order

import com.clothex.data.domain.model.order.MyOrder
import com.clothex.data.domain.repository.order.IGetMyOrdersRepository
import com.clothex.data.remote.api.OrdersApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by Mohamed Elshafey on 11/17/2020.
 */
class GetMyOrdersRepository(private val apiService: OrdersApiService) : IGetMyOrdersRepository {
    override suspend fun getMyOrders(): Flow<List<MyOrder>?> =
        flow { emit(apiService.getMyOrders()) }
}