package com.clothex.user.utils

import android.content.Context
import android.os.Build
import java.util.*

fun Context.getCurrentLocale(): Locale? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        resources.configuration.locales[0]
    } else {
        resources.configuration.locale
    }
}