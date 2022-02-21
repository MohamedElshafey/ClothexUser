package com.clothex.data.remote.repository.my_item

import com.clothex.data.domain.model.SimpleResponse
import com.clothex.data.domain.model.body.MyItemBody
import com.clothex.data.domain.model.my_item.MyItem
import com.clothex.data.domain.repository.my_item.ICreateMyItemRepository
import com.clothex.data.remote.api.MyItemApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by Mohamed Elshafey on 11/17/2020.
 */
class CreateMyItemRepository(private val apiService: MyItemApiService) : ICreateMyItemRepository {
    override suspend fun createMyItem(myItemBody: MyItemBody): Flow<MyItem?> =
        flow {
            emit(apiService.createMyItem(myItemBody))
        }
}