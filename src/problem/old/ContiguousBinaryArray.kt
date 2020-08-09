package problem.old

import java.util.*


/**
 * Created by randheercode
 * Date: 26/5/20
 * Time: 10:28 pm
 * Problem Statement: Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.
 */
class ContiguousBinaryArray {
    fun findMaxLength(nums: IntArray): Int {
        val map: MutableMap<Int, Int> = HashMap()
        map[0] = -1
        var maxlen = 0
        var count = 0
        for (i in nums.indices) {
            count += if (nums[i] == 1) 1 else -1
            if (map.containsKey(count)) {
                maxlen = maxOf(maxlen, i - map[count]!!)
            } else {
                map[count] = i
            }
        }
        return maxlen
    }
}

fun main() {
    println(ContiguousBinaryArray().findMaxLength(intArrayOf(1, 1, 1, 1, 0, 0, 0, 0, 1, 0, 1, 0, 1)))
}