package com.clothex.data.domain.usecases.order

import com.clothex.data.domain.model.SimpleResponse
import com.clothex.data.domain.model.body.OrderBody
import com.clothex.data.domain.repository.order.ICreateMyOrderRepository
import com.clothex.data.domain.usecases.BaseUseCase
import kotlinx.coroutines.flow.Flow

/**
 * Created by Mohamed Elshafey on 11/17/2020.
 */

typealias CreateMyOrdersBaseUseCase = BaseUseCase<OrderBody, Flow<SimpleResponse?>>

class CreateMyOrdersUseCase(private val repository: ICreateMyOrderRepository) :
    CreateMyOrdersBaseUseCase {
    override suspend fun invoke(params: OrderBody): Flow<SimpleResponse?> =
        repository.createMyOrder(params)
}