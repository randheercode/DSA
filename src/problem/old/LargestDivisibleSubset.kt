package problem.old

import java.util.*


/**
 * Created by randheercode
 * Date: 13/6/20
 * Time: 6:07 pm
 * Problem: Given a set of distinct positive integers, find the largest subset such that every pair (Si, Sj)
 * of elements in this subset satisfies: Si % Sj = 0 or Sj % Si = 0.
 * If there are multiple solutions, return any subset is fine.
 */
class LargestDivisibleSubset {
    var mem: MutableMap<Int, List<Int>> = HashMap()

    private fun helper(nums: IntArray, i: Int): List<Int> {
        if (mem.containsKey(i)) return mem[i]!!
        var maxLenLst: List<Int> = ArrayList()
        val div = if (i == 0) 1 else nums[i - 1]
        for (k in i until nums.size) {
            if (nums[k] % div == 0) {
                val lst: MutableList<Int> = ArrayList(helper(nums, k + 1))
                lst.add(nums[k])
                if (lst.size > maxLenLst.size) maxLenLst = lst
            }
        }
        mem[i] = maxLenLst
        return maxLenLst
    }

    fun largestDivisibleSubset(nums: IntArray): List<Int> {
        Arrays.sort(nums)
        return helper(nums, 0)
    }

    fun largestDivisibleSubset1(nums: IntArray): List<Int> {
        return if (nums.size < 2) nums.toList() else {
            nums.sort()
            val parent = IntArray(nums.size)
            val count = IntArray(nums.size)
            var max = 0
            var maxind = -1
            for (i in nums.indices.reversed()) {
                for (j in i until nums.size) {
                    if (nums[j] % nums[i] == 0 && count[i] < 1 + count[j]) {
                        count[i] = 1 + count[j]
                        parent[i] = j
                        if (count[i] > max) {
                            max = count[i]
                            maxind = i
                        }
                    }
                }
            }
            val res = IntArray(max)
            for (i in 0 until max) {
                res[i] = nums[maxind]
                maxind = parent[maxind]
            }
            res.toList()
        }
    }
}