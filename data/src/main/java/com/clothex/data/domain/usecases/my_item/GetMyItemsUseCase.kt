package com.clothex.data.domain.usecases.my_item

import com.clothex.data.domain.model.my_item.MyItem
import com.clothex.data.domain.repository.my_item.IGetMyItemsRepository
import com.clothex.data.domain.usecases.BaseUseCase
import kotlinx.coroutines.flow.Flow

/**
 * Created by Mohamed Elshafey on 11/17/2020.
 */

typealias GetMyItemsBaseUseCase = BaseUseCase<Unit, Flow<List<MyItem>?>>

class GetMyItemsUseCase(private val repository: IGetMyItemsRepository) : GetMyItemsBaseUseCase {
    override suspend fun invoke(params: Unit): Flow<List<MyItem>?> = repository.getMyItems()
}