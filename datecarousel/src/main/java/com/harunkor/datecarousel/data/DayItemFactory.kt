package com.harunkor.datecarousel.data
/**
 * --------------------------------------------
 * Developer: Harun Kör
 * Title    : Senior Android Developer
 * Website  : www.harunkor.com.tr
 * GitHub   : https://github.com/harunkor
 * --------------------------------------------
 *
 */
import com.harunkor.datecarousel.model.DayItem
import java.util.Calendar
import java.util.Date

fun nextDays(count: Int, start: Date = Date()): List<DayItem> {
    val cal = Calendar.getInstance().apply { time = start }
    return buildList {
        repeat(count) {
            add(DayItem(date = cal.time))
            cal.add(Calendar.DAY_OF_MONTH, 1)
        }
    }
}

fun nextDaysMockHasDot(count: Int, start: Date = Date()): List<DayItem> {
    val cal = Calendar.getInstance().apply { time = start }
    return buildList {
        repeat(count) { i ->
            add(DayItem(date = cal.time, hasDot = i % 2 == 0))
            cal.add(Calendar.DAY_OF_MONTH, 1)
        }
    }
}