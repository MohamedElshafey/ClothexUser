package com.clothex.data.domain.usecases.my_item

import com.clothex.data.domain.model.SimpleResponse
import com.clothex.data.domain.repository.my_item.IDeleteMyItemsRepository
import com.clothex.data.domain.usecases.BaseUseCase
import kotlinx.coroutines.flow.Flow

/**
 * Created by Mohamed Elshafey on 11/17/2020.
 */

typealias DeleteMyItemsBaseUseCase = BaseUseCase<List<String>, Flow<SimpleResponse?>>

class DeleteMyItemsUseCase(private val repository: IDeleteMyItemsRepository) :
    DeleteMyItemsBaseUseCase {
    override suspend fun invoke(params: List<String>): Flow<SimpleResponse?> =
        repository.deleteMyItems(params)
}