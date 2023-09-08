package univ.suwon.sulasang.common.common.util

import java.time.LocalDate


object StartEndDateConverter {

    // 2023-09-04(월) ~ 2023-09-08(금)
    fun extractWeeks(mergedDateTimeInfo: String): Pair<LocalDate, LocalDate> {
        val startYear = mergedDateTimeInfo.substring(0, 4).toInt()
        val startMonth = mergedDateTimeInfo.substring(5, 7).toInt()
        val startDayOfMonth = mergedDateTimeInfo.substring(8, 10).toInt()
        // val startDayOfWeek = mergedDateTimeInfo.substring(11, 12)

        val endYear = mergedDateTimeInfo.substring(16, 20).toInt()
        val endMonth = mergedDateTimeInfo.substring(21, 23).toInt()
        val endDay = mergedDateTimeInfo.substring(24, 26).toInt()
        // val endDayOfWeek = mergedDateTimeInfo.substring(27, 28)

        val startDate = LocalDate.of(
            startYear,
            startMonth,
            startDayOfMonth
        )

        val endDate = LocalDate.of(
            endYear,
            endMonth,
            endDay
        )

        return Pair(startDate, endDate)
    }
}