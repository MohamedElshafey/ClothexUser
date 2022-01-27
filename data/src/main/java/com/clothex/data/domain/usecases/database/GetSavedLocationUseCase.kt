package com.clothex.data.domain.usecases.database

import com.clothex.data.domain.usecases.BaseUseCase
import com.clothex.data.local.room.dao.SavedLocationDao
import com.clothex.data.local.room.entity.SavedLocation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by Mohamed Elshafey on 11/17/2020.
 */

typealias GetSavedLocationBaseUseCase = BaseUseCase<Boolean, Flow<SavedLocation?>>

class GetSavedLocationUseCase(private val repository: SavedLocationDao) :
    GetSavedLocationBaseUseCase {
    override suspend fun invoke(params: Boolean): Flow<SavedLocation?> = flow {
        emit(repository.get(params))
    }
}