package com.clothex.data.domain.repository.order

import com.clothex.data.domain.model.order.MyOrder
import kotlinx.coroutines.flow.Flow

/**
 * Created by Mohamed Elshafey on 10/10/2020.
 */
interface IGetMyOrdersRepository {
    suspend fun getMyOrders(): Flow<List<MyOrder>?>
}