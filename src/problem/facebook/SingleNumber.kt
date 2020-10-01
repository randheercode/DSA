package problem.facebook

// https://leetcode.com/problems/single-number/
class SingleNumber {
    fun singleNumber(nums: IntArray): Int {
        return nums.reduce { acc, i -> acc xor i }
    }
}

fun main() {
    println(SingleNumber().singleNumber(intArrayOf(2, 2, 1)))
    println(SingleNumber().singleNumber(intArrayOf(4, 1, 2, 1, 2)))
}