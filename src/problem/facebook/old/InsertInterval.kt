package problem.facebook.old

// https://leetcode.com/problems/insert-interval/
class InsertInterval {
    /**
     * Another way of solving this problem is if we can insert interval at idx and them directly merge escaping the while in current solution.
     */
    fun insert(intervals: Array<IntArray>, newInterval: IntArray): Array<IntArray> {
        if (intervals.isEmpty()) return arrayOf(newInterval)
        var idx = 0
        while (idx < intervals.size && intervals[idx][0] < newInterval[0]) {
            idx++
        }
        val result = Array(intervals.size + 1) { IntArray(2) }
        var i = 0
        while (i < result.size) {
            when {
                i < idx -> {
                    result[i] = intervals[i]
                }
                i > idx -> {
                    result[i] = intervals[i - 1]
                }
                else -> {
                    result[i] = newInterval
                }
            }
            i++
        }
        return merge(result)
    }

    private fun merge(intervals: Array<IntArray>): Array<IntArray> {
        if (intervals.size <= 1) return intervals
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
    println(InsertInterval().insert(
            arrayOf(
                    intArrayOf(1, 3),
                    intArrayOf(6, 9)
            ),
            intArrayOf(2, 5)
    ).map { it.toList() })
    println(InsertInterval().insert(
            arrayOf(
                    intArrayOf(1, 2),
                    intArrayOf(3, 5),
                    intArrayOf(6, 7),
                    intArrayOf(8, 10),
                    intArrayOf(12, 16)
            ),
            intArrayOf(4, 8)
    ).map { it.toList() })
    println(InsertInterval().insert(
            arrayOf(),
            intArrayOf(5, 7)
    ).map { it.toList() })
    println(InsertInterval().insert(
            arrayOf(
                    intArrayOf(1, 5)
            ),
            intArrayOf(2, 3)
    ).map { it.toList() })
    println(InsertInterval().insert(
            arrayOf(
                    intArrayOf(1, 5)
            ),
            intArrayOf(2, 7)
    ).map { it.toList() })
    println(InsertInterval().insert(
            arrayOf(
                    intArrayOf(1, 5)
            ),
            intArrayOf(1, 7)
    ).map { it.toList() })
    println(InsertInterval().insert(
            arrayOf(
                    intArrayOf(0, 5),
                    intArrayOf(9, 12)
            ),
            intArrayOf(7, 16)
    ).map { it.toList() })
}