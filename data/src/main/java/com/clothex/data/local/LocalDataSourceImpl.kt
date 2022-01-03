package com.clothex.data.local

import android.content.SharedPreferences
import com.clothex.data.domain.model.body.SortEnum
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class LocalDataSourceImpl(private val sharedPreferences: SharedPreferences) : ILocalDataSource {

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

    override suspend fun setSize(string: String) = withContext(Dispatchers.IO) {
        sharedPreferences.edit().putString(SIZE_KEY, string).apply()
    }

    override suspend fun getSize(): Flow<String?> = flow {
        emit(sharedPreferences.getString(SIZE_KEY, null))
    }

    override suspend fun setColor(string: String) = withContext(Dispatchers.IO) {
        sharedPreferences.edit().putString(COLOR_KEY, string).apply()
    }

    override suspend fun getColor(): Flow<String?> = flow {
        emit(sharedPreferences.getString(COLOR_KEY, null))
    }

    override suspend fun setPriceStart(price: Int?) = withContext(Dispatchers.IO) {
        sharedPreferences.edit().putInt(PRICE_START_KEY, price ?: -1).apply()
    }

    override suspend fun getPriceStart(): Flow<Int?> = flow {
        val price = sharedPreferences.getInt(PRICE_START_KEY, -1)
        emit(if (price == -1) null else price)
    }

    override suspend fun setPriceEnd(price: Int?) = withContext(Dispatchers.IO) {
        sharedPreferences.edit().putInt(PRICE_END_KEY, price ?: -1).apply()
    }

    override suspend fun getPriceEnd(): Flow<Int?> = flow {
        val price = sharedPreferences.getInt(PRICE_END_KEY, -1)
        emit(if (price == -1) null else price)
    }

    override suspend fun clearSessionPref() {
        sharedPreferences.edit().remove(SORT_ENUM_KEY).apply()
        sharedPreferences.edit().remove(PRICE_START_KEY).apply()
        sharedPreferences.edit().remove(PRICE_END_KEY).apply()
        sharedPreferences.edit().remove(SIZE_KEY).apply()
        sharedPreferences.edit().remove(COLOR_KEY).apply()
    }

    override suspend fun clearUserPref() {

    }

    override suspend fun clearAll() {

    }
}