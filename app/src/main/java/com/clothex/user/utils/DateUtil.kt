package com.clothex.user.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtil {

    fun getDifferenceTimeStamp(end: String, start: String): Long? {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH)
        val endDate = simpleDateFormat.parse(end)
        val startDate = simpleDateFormat.parse(start)
        if (endDate == null || startDate == null) return null
        return endDate.time - startDate.time
    }

    fun getDifferenceTimeStamp(end: String): Long? {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH)
        val endDate = simpleDateFormat.parse(end) ?: return null
        val sf = SimpleDateFormat("yyyy-MMM-dd HH:mm:ss", Locale.ENGLISH)
        sf.timeZone = TimeZone.getTimeZone("UTC")
        val localDateFormat = SimpleDateFormat("yyyy-MMM-dd HH:mm:ss", Locale.ENGLISH)
        val date = localDateFormat.parse(sf.format(Date())) ?: return null
        return endDate.time - date.time
    }

}