package com.clothex.data.domain.usecases.order

import com.clothex.data.domain.model.order.MyOrder
import com.clothex.data.domain.repository.order.IGetMyOrdersRepository
import com.clothex.data.domain.usecases.BaseUseCase
import kotlinx.coroutines.flow.Flow

/**
 * Created by Mohamed Elshafey on 11/17/2020.
 */

typealias GetMyOrdersBaseUseCase = BaseUseCase<String, Flow<List<MyOrder>?>>

class GetMyOrdersUseCase(private val repository: IGetMyOrdersRepository) : GetMyOrdersBaseUseCase {
    override suspend fun invoke(params: String): Flow<List<MyOrder>?> =
        repository.getMyOrders(params)
}