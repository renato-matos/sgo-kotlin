package com.sgo.sgo.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.*

fun convertDateFromInput(inputDate: String) : Instant {
    val sdf = SimpleDateFormat("yyyy-mm-dd")
    sdf.timeZone = TimeZone.getTimeZone("GMT")
    try {
        return sdf.parse(inputDate).toInstant()
    } catch (e: ParseException) {
        return Instant.now()
    }

}