package problem.subarray

class MaximumSubArray {
    fun maxSubArray(nums: IntArray): Int {
        if (nums.isEmpty()) return 0
        var maxSum = nums[0]
        var currentSum = nums[0]

        for (i in 1 until nums.lastIndex) {
            currentSum = maxOf(nums[i], currentSum + nums[i])
            maxSum = maxOf(maxSum, currentSum)
        }

        return maxSum
    }
}

fun main() {
    println(MaximumSubArray().maxSubArray(intArrayOf(-2, 1, -3, 4, -1, 2, 1, -5, 4)))
//    println(MaximumSubArray().maxSubArray(intArrayOf(1)))
//    println(MaximumSubArray().maxSubArray(intArrayOf(0)))
//    println(MaximumSubArray().maxSubArray(intArrayOf(-1)))
//    println(MaximumSubArray().maxSubArray(intArrayOf(-2147483647)))
}