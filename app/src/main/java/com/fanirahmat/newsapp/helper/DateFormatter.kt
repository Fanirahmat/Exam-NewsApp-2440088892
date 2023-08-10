package com.fanirahmat.newsapp.helper

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DateFormatter {
    fun convertStringToDate(dateString: String, format: String): Date? {
        val dateFormat = SimpleDateFormat(format, Locale.getDefault())
        return try {
            dateFormat.parse(dateString)
        } catch (e: Exception) {
            null
        }
    }

    fun formatDateToDayAndDate(date: Date): String {
        val dateFormat = SimpleDateFormat("EEEE, MMMM d, yyyy", Locale.getDefault())
        return dateFormat.format(date)
    }

}