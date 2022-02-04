package com.clothex.data.domain.usecases.order

import com.clothex.data.domain.model.order.MyOrder
import com.clothex.data.domain.repository.order.IGetOrderDetailsRepository
import com.clothex.data.domain.usecases.BaseUseCase
import kotlinx.coroutines.flow.Flow

/**
 * Created by Mohamed Elshafey on 11/17/2020.
 */

typealias GetOrderDetailsBaseUseCase = BaseUseCase<String, Flow<Result<MyOrder>>>

class GetOrderDetailsUseCase(private val repository: IGetOrderDetailsRepository) :
    GetOrderDetailsBaseUseCase {
    override suspend fun invoke(params: String): Flow<Result<MyOrder>> =
        repository.getOrderDetails(params)
}