package problems.lc_contest.bw.bw30

import java.util.*


/**
 * Created by randheercode
 * LeetCode Contest.
 */
private class Prob1 {
    fun reformatDate(date: String): String {
        val monthData = arrayOf("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec")
        val dateComponent = date.split(" ")
        val day = dateComponent[0].toLowerCase().replace("th", "")
                .replace("nd", "")
                .replace("rd", "")
                .replace("st", "").toIntOrNull()
        val month = monthData.indexOf(dateComponent[1]) + 1
        return "${dateComponent[3]}-${String.format("%02d", month)}-${String.format("%02d", day)}"
    }
}

private fun test1() {
    println(Prob1().reformatDate("20th Oct 2052"))
}

private class Prob2 {
    fun rangeSum(nums: IntArray, n: Int, left: Int, right: Int): Int {
        val module = Math.pow(10.0, 9.0).toInt().plus(7)
        val subArraySum = mutableListOf<Int>()
        val lastIndex = nums.lastIndex
        for (i in nums.indices) {
            var sum = 0
            var j = i
            while (j <= lastIndex) {
                sum = (sum + nums[j]).rem(module)
                subArraySum.add(sum)
                j++
            }
        }
        subArraySum.sort()
        return subArraySum.slice(left.minus(1)..right.minus(1)).sum().rem(module)
    }
}

private fun test2() {
    println(Prob2().rangeSum(intArrayOf(1, 2, 3, 4), 4, 1, 5))
    println(Prob2().rangeSum(intArrayOf(1, 2, 3, 4), 4, 3, 4))
    println(Prob2().rangeSum(intArrayOf(1, 2, 3, 4), 4, 1, 10))
}

private class Prob3 {
    fun minDifference(nums: IntArray): Int {
        val n: Int = nums.size
        var res = Int.MAX_VALUE
        if (n < 5) return 0
        nums.sort()
        for (i in 0..3) {
            res = minOf(res, nums[n - 4 + i] - nums[i])
        }
        return res
    }
}

private fun test3() {
    println(Prob3().minDifference(intArrayOf(5, 3, 2, 4)))
    println(Prob3().minDifference(intArrayOf(1, 5, 0, 10, 14)))
    println(Prob3().minDifference(intArrayOf(6, 6, 0, 1, 1, 4, 6)))
    println(Prob3().minDifference(intArrayOf(1, 5, 6, 14, 15)))
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
