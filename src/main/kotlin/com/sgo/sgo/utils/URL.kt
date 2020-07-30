package com.sgo.sgo.utils

import java.io.UnsupportedEncodingException
import java.net.URLDecoder
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.*

fun convertDateFromInput(inputDate: String): Instant {
    val sdf = SimpleDateFormat("yyyy-mm-dd")
    sdf.timeZone = TimeZone.getTimeZone("GMT")
    return try {
        sdf.parse(inputDate).toInstant()
    } catch (e: ParseException) {
        Instant.now()
    }
}

fun decodeParam(text: String): String {
    return try {
        URLDecoder.decode(text, "UTF-8")
    } catch (e: UnsupportedEncodingException) {
        ""
    }
}