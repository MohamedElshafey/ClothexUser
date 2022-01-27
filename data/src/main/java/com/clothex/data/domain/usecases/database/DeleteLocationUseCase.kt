package com.clothex.data.domain.usecases.database

import com.clothex.data.domain.usecases.BaseUseCase
import com.clothex.data.local.room.dao.SavedLocationDao
import com.clothex.data.local.room.entity.SavedLocation

/**
 * Created by Mohamed Elshafey on 11/17/2020.
 */

typealias DeleteLocationBaseUseCase = BaseUseCase<SavedLocation, Unit>

class DeleteLocationUseCase(private val repository: SavedLocationDao) : DeleteLocationBaseUseCase {
    override suspend fun invoke(params: SavedLocation) = repository.delete(params)
}