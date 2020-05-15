package problems

/**
 * Created by randheercode
 * Date: 15/5/20
 * Time: 5:38 pm
 * https://leetcode.com/problems/maximum-sum-circular-subarray/discuss/633401/Kadane-Algorithm-Trick-beats-100-Java-Explained
 */
class CircularSubArrayMaxSum {
    fun maxSubarraySumCircular(A: IntArray): Int {
        val nonCircularSum = kadaneMaxSum(A)
        var totalSum = 0
        for (i in A.indices) {
            totalSum += A[i]
            A[i] = -A[i]
        }
        val circularSum = totalSum + kadaneMaxSum(A)
        return if (circularSum <= 0) nonCircularSum else Math.max(circularSum, nonCircularSum)
    }

    private fun kadaneMaxSum(A: IntArray): Int {
        var currentSum = A[0]
        var maxSum = A[0]
        for (i in 1 until A.size) {
            if (currentSum < 0) currentSum = 0
            currentSum += A[i]
            maxSum = maxSum.coerceAtLeast(currentSum)
        }
        return maxSum
    }
}

fun main() {
    println(CircularSubArrayMaxSum().maxSubarraySumCircular(intArrayOf(1, -2, 3, -2)))
    println(CircularSubArrayMaxSum().maxSubarraySumCircular(intArrayOf(5, -3, 5)))
    println(CircularSubArrayMaxSum().maxSubarraySumCircular(intArrayOf(3, -1, 2, -1)))
    println(CircularSubArrayMaxSum().maxSubarraySumCircular(intArrayOf(3, -2, 2, -3)))
    println(CircularSubArrayMaxSum().maxSubarraySumCircular(intArrayOf(-2, -3, -1)))
    println(CircularSubArrayMaxSum().maxSubarraySumCircular(intArrayOf(2, -2, 2, 7, 8, 0)))
    println(CircularSubArrayMaxSum().maxSubarraySumCircular(intArrayOf(-2, 4, -5, 4, -5, 9, 4)))
}