package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) {
    infix operator fun compareTo(target: MyDate): Int {
        var res: Int = 0
        res = year.compareTo(target.year)
        if (res == 0) {
            res = month.compareTo(target.month)
            if (res == 0) {
                return dayOfMonth.compareTo(target.dayOfMonth)
            } else {
                return res
            }
        } else {
            return res
        }
    }
}

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this,other)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

class DateRange(val start: MyDate, val endInclusive: MyDate) {
    infix operator fun contains(date: MyDate): Boolean {
        return date >= start && date <= endInclusive
    }
}
