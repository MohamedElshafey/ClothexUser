package com.clothex.user.utils

import java.text.ParseException
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

    fun Date.fromUTCToLocal(): String? {
        val format = "yyyy-MM-dd hh:mm a"
        var dateToReturn = this.toString()
        val sdf = SimpleDateFormat(format, Locale.ENGLISH)
        sdf.timeZone = TimeZone.getTimeZone("UTC")
        var gmt: Date
        val sdfOutPutToSend = SimpleDateFormat(format, Locale.ENGLISH)
        sdfOutPutToSend.timeZone = TimeZone.getDefault()
        try {
            gmt = sdf.parse(this.toString())
            dateToReturn = sdfOutPutToSend.format(gmt)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return dateToReturn
    }


}