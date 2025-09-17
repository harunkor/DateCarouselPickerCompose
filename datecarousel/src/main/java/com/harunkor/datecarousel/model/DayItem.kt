package com.harunkor.datecarousel.model

/**
 * --------------------------------------------
 * Developer: Harun Kör
 * Title    : Senior Android Developer
 * Website  : www.harunkor.com.tr
 * GitHub   : https://github.com/harunkor
 * --------------------------------------------
 *
 */
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@Parcelize
data class DayItem(
    val date: Date,
    val hasDot: Boolean = false,
    val customWeekdayLabel: String? = null,
) : Parcelable {
    val dayOfMonth: String
        get() {
            val cal = Calendar.getInstance().apply { time = date }
            return cal.get(Calendar.DAY_OF_MONTH).toString()
        }

    val monthLabel: String
        get() = SimpleDateFormat("MMM", Locale.getDefault()).format(date).uppercase()


    val weekdayLabel: String
        get() = customWeekdayLabel ?: SimpleDateFormat("EEE", Locale.getDefault()).format(date)
}
