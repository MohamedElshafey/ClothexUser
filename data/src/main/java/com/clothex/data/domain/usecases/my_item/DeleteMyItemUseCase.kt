package com.clothex.data.domain.usecases.my_item

import com.clothex.data.domain.model.SimpleResponse
import com.clothex.data.domain.repository.my_item.IDeleteMyItemRepository
import com.clothex.data.domain.usecases.BaseUseCase
import kotlinx.coroutines.flow.Flow

/**
 * Created by Mohamed Elshafey on 11/17/2020.
 */

typealias DeleteMyItemBaseUseCase = BaseUseCase<String, Flow<SimpleResponse?>>

class DeleteMyItemUseCase(private val repository: IDeleteMyItemRepository) :
    DeleteMyItemBaseUseCase {
    override suspend fun invoke(params: String): Flow<SimpleResponse?> =
        repository.deleteMyItem(params)
}