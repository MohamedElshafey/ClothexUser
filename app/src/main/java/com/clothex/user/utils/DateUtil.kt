package com.clothex.user.utils

import android.content.Context
import java.text.SimpleDateFormat
import java.util.*

object DateUtil {

    fun getDifferenceTimeStamp(context: Context, end: String): Long? {
        val endDateLocalTimeZone = end.toLocalTimeZone(context)
        val dateFormat = SimpleDateFormat(outputDateFormat, Locale.ENGLISH)
        return try {
            val endDate = dateFormat.parse(endDateLocalTimeZone)
            endDate!!.time - Date().time
        } catch (e: Exception) {
            null
        }
    }

    fun String.toLocalTimeZone(context: Context): String {
        val locale = context.getCurrentLocale()
        val inputSdf = SimpleDateFormat(inputDateFormat, Locale.ENGLISH)
        inputSdf.timeZone = TimeZone.getTimeZone("UTC")
        try {
            val date = inputSdf.parse(this) ?: return this
            val df = SimpleDateFormat(outputDateFormat, locale)
            df.timeZone = TimeZone.getDefault()
            return df.format(date)
        } catch (e: Exception) {
            return this
        }
    }
}