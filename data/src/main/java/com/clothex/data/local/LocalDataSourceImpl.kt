package com.clothex.data.local

import android.content.SharedPreferences
import com.clothex.data.domain.model.body.SortEnum
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class LocalDataSourceImpl(val sharedPreferences: SharedPreferences) : ILocalDataStore {

    override suspend fun setToken(token: String?) = withContext(Dispatchers.IO) {
        sharedPreferences.edit().putString(TOKEN_KEY, token).apply()
    }

    override suspend fun getToken(): Flow<String?> = flow {
        emit(sharedPreferences.getString(TOKEN_KEY, null))
    }

    override suspend fun setSortEnum(sortEnum: SortEnum) = withContext(Dispatchers.IO) {
        sharedPreferences.edit().putString(SORT_ENUM_KEY, sortEnum.value).apply()
    }

    override suspend fun getSortEnum(): Flow<String?> = flow {
        emit(sharedPreferences.getString(SORT_ENUM_KEY, null))
    }

    override suspend fun clearSessionPref() {
        sharedPreferences.edit().remove(SORT_ENUM_KEY).apply()
    }

    override suspend fun clearUserPref() {

    }

    override suspend fun clearAll() {

    }
}