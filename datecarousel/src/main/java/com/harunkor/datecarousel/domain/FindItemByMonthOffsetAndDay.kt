package com.harunkor.datecarousel.domain

/**
 * --------------------------------------------
 * Developer: Harun Kör
 * Title    : Senior Android Developer
 * Website  : www.harunkor.com.tr
 * GitHub   : https://github.com/harunkor
 * --------------------------------------------
 *
 */
import com.harunkor.datecarousel.extensions.toLocalDate
import com.harunkor.datecarousel.model.DayItem
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import kotlin.math.abs

/**
 * monthOffset: 0 = bu ay, 1 = gelecek ay,
 * dayOfMonth: hedef gün (ayda yoksa en yakın gün için bkz. closest parametresi)
 */
fun findItemByMonthOffsetAndDay(
    items: List<DayItem>,
    monthOffset: Int,
    dayOfMonth: Int,
    today: LocalDate = LocalDate.now(),
    pickClosestIfMissing: Boolean = false
): DayItem? {
    val targetMonth = today.plusMonths(monthOffset.toLong())
    val safeDay = dayOfMonth.coerceIn(1, targetMonth.lengthOfMonth())
    val targetDate = targetMonth.withDayOfMonth(safeDay)

    val exact = items.firstOrNull { it.date.toLocalDate() == targetDate }
    if (exact != null || !pickClosestIfMissing) return exact


    return items.minByOrNull {
        abs(ChronoUnit.DAYS.between(it.date.toLocalDate(), targetDate))
    }
}

fun findIndexByMonthOffsetAndDay(
    items: List<DayItem>,
    monthOffset: Int,
    dayOfMonth: Int,
    today: LocalDate = LocalDate.now(),
    pickClosestIfMissing: Boolean = false
): Int? {
    val item =
        findItemByMonthOffsetAndDay(items, monthOffset, dayOfMonth, today, pickClosestIfMissing)
    return item?.let { items.indexOf(it) }?.takeIf { it >= 0 }
}
