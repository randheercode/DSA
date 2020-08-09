package problems.lc_contest.w.w201

import java.util.*


/**
 * Created by randheercode
 * LeetCode Contest.
 */
private class Prob1 {
    fun makeGood(s: String): String {
        if (s.length < 2) return s
        val str = StringBuilder(s)
        var i = 0
        while (i + 1 <= str.lastIndex) {
            if (Math.abs(str[i].toInt().minus(str[i + 1].toInt())) == 32) {
                str.deleteCharAt(i)
                str.deleteCharAt(i)
                i = 0
            } else {
                i++
            }
        }
        return str.toString()
    }
}

private fun test1() {
    println(Prob1().makeGood("leEeetcode"))
    println(Prob1().makeGood("abBAcC"))
    println(Prob1().makeGood("Pp"))
}

private class Prob2 {
    fun findKthBit(n: Int, k: Int): Char {
        fun invert(s: String): String {
            val str = StringBuilder(s)
            for (i in str.indices) {
                if (str[i] == '0') str[i] = '1'
                else str[i] = '0'
            }
            return str.toString()
        }

        fun makeString(i: Int): String {
            if (i == 1) return "0"
            val last = makeString(i - 1)
            return last + "1" + invert(last).reversed()
        }
        return makeString(n)[k - 1]
    }
}

private fun test2() {
    println(Prob2().findKthBit(3, 1))
    println(Prob2().findKthBit(4, 11))
    println(Prob2().findKthBit(1, 1))
    println(Prob2().findKthBit(2, 3))
}

private class Prob3 {
    fun maxNonOverlapping(nums: IntArray, target: Int): Int {
        val map: MutableMap<Int, Int?> = HashMap()
        map[0] = 0

        var res = 0
        var sum = 0

        for (element in nums) {
            sum += element
            if (map.containsKey(sum - target)) {
                res = maxOf(res, map[sum - target]!! + 1)
            }
            map[sum] = res
        }

        return res
    }
}

private fun test3() {
    println(Prob3().maxNonOverlapping(intArrayOf(1, 1, 1, 1, 1), 2))
    println(Prob3().maxNonOverlapping(intArrayOf(-1, 3, 5, 1, 4, 2, -9), 6))
}

private class Prob4 {
    fun minCost(n: Int, cuts: IntArray?): Int {
        fun helper(cuts: IntArray, memo: MutableMap<String, Int?>, l: Int, r: Int): Int {
            var res = Int.MAX_VALUE
            val key = "$l-$r"
            if (memo.containsKey(key)) return memo[key]!!
            for (i in cuts.indices) {
                if (cuts[i] <= l || cuts[i] >= r) continue
                val cost = r - l
                res = minOf(helper(cuts, memo, l, cuts[i]) + cost + helper(cuts, memo, cuts[i], r), res)
            }
            res = if (res == Int.MAX_VALUE) 0 else res
            memo[key] = res
            return res
        }
        return helper(cuts!!, mutableMapOf(), 0, n)
    }
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
