package com.clothex.user.utils

import android.content.Context
import java.text.SimpleDateFormat
import java.util.*

object DateUtil {

    fun getDifferenceTimeStamp(end: String): Long? {
        val inputSdf = SimpleDateFormat(inputDateFormat, Locale.ENGLISH)
        inputSdf.timeZone = TimeZone.getTimeZone("UTC")
        val endDate = inputSdf.parse(end)
        val df = SimpleDateFormat(inputDateFormat, Locale.ENGLISH)
        df.timeZone = TimeZone.getDefault()
        return endDate?.time?.minus(Date().time)
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

    fun String.toLocalDateOnly(context: Context): String {
        val locale = context.getCurrentLocale()
        val inputSdf = SimpleDateFormat(inputDateFormat, Locale.ENGLISH)
        inputSdf.timeZone = TimeZone.getTimeZone("UTC")
        try {
            val date = inputSdf.parse(this) ?: return this
            val df = SimpleDateFormat(outputDateOnlyFormat, locale)
            df.timeZone = TimeZone.getDefault()
            return df.format(date)
        } catch (e: Exception) {
            return this
        }
    }
}