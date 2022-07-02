package com.clothex.data.domain.repository.logging

/**
 * Created by Mohamed Elshafey on 10/10/2020.
 */
interface ILogEventRepository {
    suspend fun logEvent(eventName: String, params: Map<String, String>?)
}