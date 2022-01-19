package com.clothex.data.remote.repository.my_item

import com.clothex.data.domain.model.my_item.MyItem
import com.clothex.data.domain.repository.my_item.IGetMyItemsRepository
import com.clothex.data.remote.api.MyItemApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by Mohamed Elshafey on 11/17/2020.
 */
class GetMyItemsRepository(private val apiService: MyItemApiService) : IGetMyItemsRepository {
    override suspend fun getMyItems(customerId: String): Flow<List<MyItem>?> =
        flow { emit(apiService.getMyItems(customerId)) }
}