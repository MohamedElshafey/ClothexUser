package com.clothex.data.domain.usecases.database

import com.clothex.data.domain.usecases.BaseUseCase
import com.clothex.data.local.room.dao.SavedLocationDao
import com.clothex.data.local.room.entity.SavedLocation

/**
 * Created by Mohamed Elshafey on 11/17/2020.
 */

typealias SaveLocationBaseUseCase = BaseUseCase<SavedLocation, Boolean>

class SaveLocationsUseCase(private val repository: SavedLocationDao) : SaveLocationBaseUseCase {
    override suspend fun invoke(params: SavedLocation): Boolean {
        val list = repository.getAll()
        if (list.size < 4 || list.find { it.uid == params.uid } != null) {
            repository.insertAll(params)
            return true
        }
        return false
    }
}