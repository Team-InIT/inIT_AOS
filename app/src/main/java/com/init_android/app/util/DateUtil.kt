package com.init_android.app.util

import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class DateUtil {

    val formatter = SimpleDateFormat("yyyy-MM-dd")

    // String-> Date (2022.05.24 -> 2022-05-24)
    fun stringToDate(dateText: String): Date {
        return formatter.parse(dateText.replace(".", "-"))
    }

    fun dateToString(date: Date):String{
        return formatter.format(date)
    }

    // 현재 날짜 가져오기
    fun getLocalTime():String{
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val formatted = current.format(formatter)
        return formatted
    }

}