package problems

/**
 * Created by randheercode
 * Date: 12/5/20
 * Time: 3:11 pm
 */
class SortedNoDuplicate {
    fun singleNonDuplicate(nums: IntArray): Int {
        return nums.reduce { acc, i -> acc xor i }
    }
}

fun main() {
    println(SortedNoDuplicate().singleNonDuplicate(intArrayOf(1, 1, 3, 2, 5, 2, 3)))
}