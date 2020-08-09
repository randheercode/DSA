package problem.old

/**
 * Created by randheercode
 * Date: 26/4/20
 * Time: 1:42 pm
 * Statement: Given an array of integers and an integer k,
 * you need to find the total number of continuous subarrays whose sum equals to k.
 * Solution: Keeping prefix sum stored and user that for next sum.
 */
class SubArraySum {
    fun subArraySum(nums: IntArray, k: Int): Int {
        var prefixSum = 0
        val prefixSumSeen = mutableMapOf<Int, Int>().apply { this[0] = 1 }
        var subarrays = 0
        for (num in nums) {
            prefixSum += num
            subarrays += prefixSumSeen[prefixSum - k] ?: 0
            prefixSumSeen[prefixSum] = 1 + (prefixSumSeen[prefixSum] ?: 0)
        }
        return subarrays
    }
}

fun main() {
    println(SubArraySum().subArraySum(intArrayOf(1, 1, 1), 2))
    println(SubArraySum().subArraySum(intArrayOf(4, 2, 3, 3, 2, 3, 2), 6))
}