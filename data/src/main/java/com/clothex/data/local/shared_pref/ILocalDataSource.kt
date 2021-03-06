package com.clothex.data.local.shared_pref

import com.clothex.data.domain.model.Language
import com.clothex.data.domain.model.body.SortEnum
import com.clothex.data.domain.model.user.User
import kotlinx.coroutines.flow.Flow

/**
 * @author Mohamed Elshafey on 9/3/2020
 */
interface ILocalDataSource {
    suspend fun setToken(token: String?)
    suspend fun getToken(): Flow<String?>

    suspend fun setLanguage(language: Language)
    fun getLanguage(): String

    suspend fun setUser(user: User?)
    suspend fun getUser(): Flow<User?>

    fun getTokenAsString(): String?

    suspend fun setSortEnum(sortEnum: SortEnum)

    suspend fun getSortEnum(): Flow<String?>

    suspend fun setSize(string: String?)
    suspend fun getSize(): Flow<String?>

    suspend fun setColor(string: String?)
    suspend fun getColor(): Flow<String?>

    suspend fun setPriceStart(price: Int?)
    suspend fun getPriceStart(): Flow<Int?>

    suspend fun setPriceEnd(price: Int?)
    suspend fun getPriceEnd(): Flow<Int?>

    suspend fun setVisitOnBoarding(isVisited: Boolean)
    suspend fun getVisitOnBoarding(): Flow<Boolean>

    suspend fun setIsLoginTemporary(isVisited: Boolean)
    suspend fun getIsLoginTemporary(): Flow<Boolean>

    suspend fun setIsFirstTime(isFirstTime: Boolean)
    suspend fun getIsFirstTime(): Flow<Boolean>

    suspend fun clearFilterPref()

    suspend fun logout()

    suspend fun clearUserPref()

    suspend fun clearAll()
}