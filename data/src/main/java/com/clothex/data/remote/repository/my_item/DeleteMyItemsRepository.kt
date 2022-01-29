package com.clothex.data.remote.repository.my_item

import com.clothex.data.domain.model.SimpleResponse
import com.clothex.data.domain.repository.my_item.IDeleteMyItemsRepository
import com.clothex.data.remote.api.MyItemApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by Mohamed Elshafey on 11/17/2020.
 */
class DeleteMyItemsRepository(private val apiService: MyItemApiService) : IDeleteMyItemsRepository {
    override suspend fun deleteMyItems(idsArray: List<String>): Flow<SimpleResponse?> = flow {
        emit(apiService.deleteMyItems(idsArray))
    }
}