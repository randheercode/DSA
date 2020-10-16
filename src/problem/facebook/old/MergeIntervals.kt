package problem.facebook.old

import java.util.*

// https://leetcode.com/problems/merge-intervals/
class MergeIntervals {
    fun merge(intervals: Array<IntArray>): Array<IntArray> {
        if (intervals.size <= 1) return intervals
        Arrays.sort(intervals, compareBy { it[0] })
        val result = mutableListOf<IntArray>()
        for (interval in intervals) {
            if (result.isEmpty()) {
                result.add(interval)
                continue
            }
            if (interval[0] <= result.last()[1]) {
                result.last()[1] = maxOf(interval[1], result.last()[1])
            } else {
                result.add(interval)
            }
        }
        return result.toTypedArray()
    }
}

fun main() {
    println(MergeIntervals().merge(arrayOf(
            intArrayOf(1, 3),
            intArrayOf(2, 6),
            intArrayOf(8, 10),
            intArrayOf(10, 18)
    )).map { it.toList() })
    println(MergeIntervals().merge(arrayOf(
            intArrayOf(0, 4),
            intArrayOf(1, 4)
    )).map { it.toList() })
    println(MergeIntervals().merge(arrayOf(
            intArrayOf(1, 4),
            intArrayOf(2, 3)
    )).map { it.toList() })
    println(MergeIntervals().merge(arrayOf(
            intArrayOf(1, 3)
    )).map { it.toList() })
}