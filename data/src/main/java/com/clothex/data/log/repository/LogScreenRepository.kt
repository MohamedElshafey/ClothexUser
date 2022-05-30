package com.clothex.data.log.repository

import com.clothex.data.domain.repository.logging.ILogScreenRepository
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase

/**
 * Created by Mohamed Elshafey on 11/17/2020.
 */
class LogScreenRepository : ILogScreenRepository {
    override suspend fun logScreen(screen: String) {
        Firebase.analytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW) {
            param(FirebaseAnalytics.Param.SCREEN_NAME, screen)
        }
    }
}