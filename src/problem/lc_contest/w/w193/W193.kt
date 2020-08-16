package problem.lc_contest.w.w193

import kotlin.collections.set


/**
 * Created by randheercode
 * LeetCode Contest.
 */
private class Prob1 {
    fun runningSum(nums: IntArray): IntArray {
        for (i in 1 until nums.size) {
            nums[i] = nums[i - 1] + nums[i]
        }
        return nums
    }
}

private fun test1() {
    println(Prob1().runningSum(intArrayOf(1, 2, 3, 4)).toList())
    println(Prob1().runningSum(intArrayOf(1, 1, 1, 1, 1)).toList())
    println(Prob1().runningSum(intArrayOf(3, 1, 2, 10, 1)).toList())
}

private class Prob2 {
    fun findLeastNumOfUniqueInts(arr: IntArray, k: Int): Int {
        var k = k
        var counts = mutableMapOf<Int, Int>()
        for (a in arr) counts[a] = counts.getOrDefault(a, 0) + 1
        counts = counts.toList().sortedBy { it.second }.toMap().toMutableMap()
        while (k > 0 && counts.isNotEmpty()) {
            val num = counts.entries.first()
            if (num.value > k) {
                k = 0
                counts[num.key] = num.value.minus(k)
            } else {
                k -= num.value
                counts.remove(num.key)
            }
        }
        return counts.size
    }
}

private fun test2() {
    println(Prob2().findLeastNumOfUniqueInts(intArrayOf(5, 5, 4), 1))
    println(Prob2().findLeastNumOfUniqueInts(intArrayOf(4, 3, 1, 1, 3, 3, 2), 3))
}

private class Prob3 {
    // TODO not done
    fun minDays(bloomDay: IntArray, m: Int, k: Int): Int {
        if (bloomDay.size < m * k) return 1
        if (m == 0 && k == 0) return 0
        return k
    }
}


private fun test3() {
    println(Prob3().minDays(intArrayOf(1, 10, 3, 10, 2), 3, 1))
    println(Prob3().minDays(intArrayOf(1, 10, 3, 10, 2), 3, 2))
    println(Prob3().minDays(intArrayOf(7, 7, 7, 7, 12, 7, 7), 2, 3))
}

private class Prob4 {

}

private fun test4() {
    println(Prob4())
}

fun main() {
    when (3) {
        1 -> test1()
        2 -> test2()
        3 -> test3()
        4 -> test4()
    }
}
