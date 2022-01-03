package com.clothex.data.local

import com.clothex.data.domain.model.body.SortEnum
import kotlinx.coroutines.flow.Flow

/**
 * @author Mohamed Elshafey on 9/3/2020
 */
interface ILocalDataStore {
    suspend fun setToken(token: String?)

    suspend fun getToken(): Flow<String?>

    suspend fun setSortEnum(sortEnum: SortEnum)

    suspend fun getSortEnum(): Flow<String?>

    suspend fun clearSessionPref()

    suspend fun clearUserPref()

    suspend fun clearAll()
}