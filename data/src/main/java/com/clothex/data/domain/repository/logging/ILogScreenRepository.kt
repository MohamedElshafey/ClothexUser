package com.clothex.data.domain.repository.logging

/**
 * Created by Mohamed Elshafey on 10/10/2020.
 */
interface ILogScreenRepository {
    suspend fun logScreen(screen: String)
}