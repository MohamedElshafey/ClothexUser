package com.clothex.user.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtil {

    fun getDifferenceTimeStamp(end: String): Long? {
        val endDateLocalTimeZone = end.toLocalTimeZone()
        val dateFormat = SimpleDateFormat(outputDateFormat, Locale.ENGLISH)
        return try {
            val endDate = dateFormat.parse(endDateLocalTimeZone)
            endDate!!.time - Date().time
        } catch (e: Exception) {
            null
        }
    }

    fun String.toLocalTimeZone(): String {
        val inputSdf = SimpleDateFormat(inputDateFormat, Locale.ENGLISH)
        inputSdf.timeZone = TimeZone.getTimeZone("UTC")
        try {
            val date = inputSdf.parse(this) ?: return this
            val df = SimpleDateFormat(outputDateFormat, Locale.ENGLISH)
            df.timeZone = TimeZone.getDefault()
            return df.format(date)
        } catch (e: Exception) {
            return this
        }
    }
}