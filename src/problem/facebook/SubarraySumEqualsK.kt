package problem.facebook

import java.util.*

// https://leetcode.com/problems/subarray-sum-equals-k/
class SubarraySumEqualsK {
    fun subarraySum(nums: IntArray, k: Int): Int {
        var count = 0
        outer@ for (i in nums.indices) {
            var sum = 0
            inner@ for (j in i..nums.lastIndex) {
                sum += nums[j]
                if (sum == k) {
                    count++
                }
            }
        }
        return count
    }

    fun subarraySumSorted(nums: IntArray, k: Int): Int {
        var count = 0
        Arrays.sort(nums)
        outer@ for (i in nums.indices) {
            var sum = 0
            inner@ for (j in i..nums.lastIndex) {
                sum += nums[j]
                if (sum == k) {
                    count++
                }
                if (sum > k) break@inner
            }
        }
        return count
    }
}

fun main() {
    println(SubarraySumEqualsK().subarraySum(intArrayOf(1, 1, 1, 1, 1), 2))
    println(SubarraySumEqualsK().subarraySum(intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0), 0))
    println(SubarraySumEqualsK().subarraySum(intArrayOf(28, 54, 7, -70, 22, 65, -6), 100))
    println(SubarraySumEqualsK().subarraySumSorted(intArrayOf(1, 1, 1, 1, 1), 2))
    println(SubarraySumEqualsK().subarraySumSorted(intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0), 0))
    println(SubarraySumEqualsK().subarraySumSorted(intArrayOf(28, 54, 7, -70, 22, 65, -6), 100))
}