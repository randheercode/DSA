package problem.lc_contest.w.w191

/**
 * Created by randheercode
 * Date: 31/5/20
 * Time: 11:52 am
 * https://leetcode.com/contest/weekly-contest-191/problems/maximum-product-of-two-elements-in-an-array/
 */
class MaxProduct {
    fun maxProduct(nums: IntArray): Int {
        var first = Int.MIN_VALUE
        var second = Int.MIN_VALUE
        for (i in nums.indices) {
            if (nums[i] > first) {
                second = first
                first = nums[i]
            } else if (nums[i] > second) {
                second = nums[i]
            }
        }
        return (first.minus(1)) * (second.minus(1))
    }
}

fun main() {
    println(MaxProduct().maxProduct(intArrayOf(3, 4, 5, 2)))
}