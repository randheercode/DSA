package problem.facebook.fb_1

import java.util.*


/**
 * Created by randheercode
 * Date: 18/11/20
 * Time: 11:36 am
 */
class Subsets {
    var output: MutableList<List<Int>> = ArrayList()
    var n = 0
    var k: Int = 0

    private fun backtrack(first: Int, curr: ArrayList<Int>, nums: IntArray) {
        if (curr.size == k) output.add(ArrayList(curr))
        for (i in first until n) {
            curr.add(nums[i])
            backtrack(i + 1, curr, nums)
            curr.removeAt(curr.size - 1)
        }
    }

    fun subsets(nums: IntArray): List<List<Int>> {
        n = nums.size
        k = 0
        while (k < n + 1) {
            backtrack(0, ArrayList(), nums)
            ++k
        }
        return output
    }
}