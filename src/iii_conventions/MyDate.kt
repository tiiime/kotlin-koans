package iii_conventions

import iii_conventions.TimeInterval.*


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

    infix operator fun plus(value: TimeInterval): MyDate {

        when (value) {
            DAY -> {
                return addTimeIntervals(DAY, 1)
            }
            WEEK -> {
                return addTimeIntervals(DAY, 7)
            }
            YEAR -> {
                return addTimeIntervals(YEAR, 1)
            }
        }
    }

    infix operator fun plus(repeatedTimeInterval: RepeatedTimeInterval): MyDate {
        val count = repeatedTimeInterval.count
        when (repeatedTimeInterval.timeInterval) {
            DAY -> {
                return addTimeIntervals(DAY, count)
            }
            WEEK -> {
                return addTimeIntervals(DAY, 7 * count)
            }
            YEAR -> {
                return addTimeIntervals(YEAR, count)
            }
        }
    }
}

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR;

    infix operator fun times(i: Int): RepeatedTimeInterval {
        return RepeatedTimeInterval(this, i)
    }
}

class RepeatedTimeInterval(var timeInterval: TimeInterval, var count: Int)

class DateRange(val start: MyDate, val endInclusive: MyDate) {
    infix operator fun contains(date: MyDate): Boolean {
        return date >= start && date <= endInclusive
    }

    operator fun iterator(): Iterator<MyDate> {
        return DateIterator(start, endInclusive).iterator()
    }
}

class DateIterator(start: MyDate, endInclusive: MyDate) : Iterator<MyDate> {
    private var curr = start
    private var end = endInclusive

    override fun next(): MyDate {
        val temp = curr
        curr = curr.nextDay()
        return temp
    }

    override fun hasNext(): Boolean = curr <= end

}