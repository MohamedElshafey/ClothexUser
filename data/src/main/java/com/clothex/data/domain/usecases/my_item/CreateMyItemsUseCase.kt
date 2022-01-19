package com.clothex.data.domain.usecases.my_item

import com.clothex.data.domain.model.SimpleResponse
import com.clothex.data.domain.model.body.MyItemBody
import com.clothex.data.domain.model.my_item.MyItem
import com.clothex.data.domain.repository.my_item.ICreateMyItemRepository
import com.clothex.data.domain.usecases.BaseUseCase
import kotlinx.coroutines.flow.Flow

/**
 * Created by Mohamed Elshafey on 11/17/2020.
 */

typealias CreateMyItemsBaseUseCase = BaseUseCase<MyItemBody, Flow<MyItem?>>

class CreateMyItemsUseCase(private val repository: ICreateMyItemRepository) :
    CreateMyItemsBaseUseCase {
    override suspend fun invoke(params: MyItemBody): Flow<MyItem?> =
        repository.createMyItem(params)
}