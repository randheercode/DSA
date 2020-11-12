package problem.facebook.fb_1

import java.util.*

/**
 * Created by randheercode
 * Date: 12/11/20
 * Time: 11:42 am
 */
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