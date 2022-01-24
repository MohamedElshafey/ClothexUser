package com.clothex.data.domain.repository.order

import com.clothex.data.domain.model.SimpleResponse
import com.clothex.data.domain.model.body.OrderBody
import kotlinx.coroutines.flow.Flow

/**
 * Created by Mohamed Elshafey on 10/10/2020.
 */
interface ICreateMyOrderRepository {
    suspend fun createMyOrder(orderBody: OrderBody): Flow<SimpleResponse?>
}