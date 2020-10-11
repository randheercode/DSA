package problem.lc_contest.w.w210

import generateIntArray


/**
 * Created by randheercode
 * LeetCode Contest.
 */
private class Prob1 {
    fun maxDepth(s: String): Int {
        var max = 0
        var current = 0
        for (c in s) {
            if (c == '(') current++
            if (c == ')') {
                max = maxOf(max, current)
                current--
            }
            if (current < 0) current = 0
        }
        return max
    }
}

private fun test1() {
    println(Prob1().maxDepth("(1+(2*3)+((8)/4))+1"))
    println(Prob1().maxDepth("(1)+((2))+(((3)))"))
    println(Prob1().maxDepth("1+(2*3)/(2-1)"))
    println(Prob1().maxDepth("1"))
}

private class Prob2 {
    fun maximalNetworkRank(n: Int, roads: Array<IntArray>): Int {
        val cs = Array(n) { BooleanArray(n) }
        val counts = IntArray(n)
        for (r in roads) {
            counts[r[0]]++
            counts[r[1]]++
            cs[r[0]][r[1]] = true
            cs[r[1]][r[0]] = true
        }
        var res = 0
        for (i in 0 until n) for (j in i + 1 until n) res = maxOf(res, counts[i] + counts[j] - if (cs[i][j]) 1 else 0)
        return res
    }
}

private fun test2() {
    println(Prob2().maximalNetworkRank(4, generateIntArray("[[0,1],[0,3],[1,2],[1,3]]")))
    println(Prob2().maximalNetworkRank(5, generateIntArray("[[0,1],[0,3],[1,2],[1,3],[2,3],[2,4]]")))
    println(Prob2().maximalNetworkRank(8, generateIntArray("[[0,1],[1,2],[2,3],[2,4],[5,6],[5,7]]")))
    println(Prob2().maximalNetworkRank(8, generateIntArray("[[4,6],[5,2],[3,5],[7,5],[7,6]]")))
}

private class Prob3 {
    fun checkPalindromeFormation(a: String, b: String): Boolean {
        val n = a.length
        if (n == 1) {
            return true
        }
        var start = 0
        var end = n - 1
        while (start < end) {
            if (b[start] == a[end] || a[start] == b[end] || a[start] == a[end] || b[start] == b[end]) {
                start++
                end--
            } else return false
        }
        return true
    }
}

private fun test3() {
    println(Prob3().checkPalindromeFormation("abdef", "fecab"))
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
