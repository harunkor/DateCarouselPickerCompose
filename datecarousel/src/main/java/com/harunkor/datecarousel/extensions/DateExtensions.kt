package com.harunkor.datecarousel.extensions

/**
 * --------------------------------------------
 * Developer: Harun Kör
 * Title    : Senior Android Developer
 * Website  : www.harunkor.com.tr
 * GitHub   : https://github.com/harunkor
 * --------------------------------------------
 *
 */
import android.os.Build
import java.time.LocalDate
import java.util.Calendar
import java.util.Date
import java.util.TimeZone

fun Date.toLocalDate(tz: TimeZone = TimeZone.getDefault()): LocalDate {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val zoneId = tz.toZoneId()
        this.toInstant().atZone(zoneId).toLocalDate()
    } else {
        val cal = Calendar.getInstance(tz).apply { time = this@toLocalDate }
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH) + 1
        val day = cal.get(Calendar.DAY_OF_MONTH)
        LocalDate.of(year, month, day)
    }
}

fun LocalDate.toDateStartOfDay(tz: TimeZone = TimeZone.getDefault()): Date {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val zoneId = tz.toZoneId()
        Date.from(this.atStartOfDay(zoneId).toInstant())
    } else {
        val cal = Calendar.getInstance(tz).apply {
            clear()
            set(Calendar.YEAR, this@toDateStartOfDay.year)
            set(Calendar.MONTH, this@toDateStartOfDay.monthValue - 1)
            set(Calendar.DAY_OF_MONTH, this@toDateStartOfDay.dayOfMonth)
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }
        cal.time
    }
}