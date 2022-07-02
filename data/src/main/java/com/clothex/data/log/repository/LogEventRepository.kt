package com.clothex.data.log.repository

import com.clothex.data.domain.repository.logging.ILogEventRepository
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase

/**
 * Created by Mohamed Elshafey on 11/17/2020.
 */
class LogEventRepository : ILogEventRepository {

    override suspend fun logEvent(eventName: String, params: Map<String, String>?) {
        Firebase.analytics.logEvent(eventName) {
            params?.forEach { param(it.key, it.value) }
        }
    }
}