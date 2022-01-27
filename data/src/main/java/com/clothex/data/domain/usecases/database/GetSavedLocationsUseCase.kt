package com.clothex.data.domain.usecases.database

import com.clothex.data.domain.usecases.BaseUseCase
import com.clothex.data.local.room.dao.SavedLocationDao
import com.clothex.data.local.room.entity.SavedLocation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by Mohamed Elshafey on 11/17/2020.
 */

typealias GetSavedLocationsBaseUseCase = BaseUseCase<Unit, Flow<List<SavedLocation>>>

class GetSavedLocationsUseCase(private val repository: SavedLocationDao) :
    GetSavedLocationsBaseUseCase {
    override suspend fun invoke(params: Unit): Flow<List<SavedLocation>> = flow {
        emit(repository.getAll())
    }
}