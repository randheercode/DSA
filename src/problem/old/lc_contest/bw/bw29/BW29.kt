package problem.old.lc_contest.bw.bw29

/**
 * Created by randheercode
 * LeetCode Contest.
 */
private class Prob1 {
    fun average(salary: IntArray): Double {
        salary.sort()
        val len = salary.size
        return salary.slice(1..len - 2).average()
    }
}

private fun test1() {
    println(Prob1().average(intArrayOf(1000, 2000, 3000)))
    println(Prob1().average(intArrayOf(6000, 5000, 4000, 3000, 2000, 1000)))
    println(Prob1().average(intArrayOf(8000, 9000, 2000, 3000, 6000, 1000)))
}

private class Prob2 {
    fun kthFactor(n: Int, k: Int): Int {
        var count = 0
        var i = 1
        while (i <= n) {
            if (n.rem(i) == 0) count++
            if (count == k) return i
            i++
        }
        return -1
    }
}

private fun test2() {
    println(Prob2().kthFactor(12, 3))
    println(Prob2().kthFactor(7, 2))
    println(Prob2().kthFactor(4, 4))
    println(Prob2().kthFactor(1, 1))
    println(Prob2().kthFactor(1000, 3))
}

private class Prob3 {

    fun longestSubarray(arr: IntArray): Int { // Not accepted
        if (arr.size == 1) return arr[0]
        val n = arr.size
        val dp1 = IntArray(n)
        val dp2 = IntArray(n)
        dp1[0] = arr[0]
        dp2[n - 1] = arr[n - 1]
        var max = arr[0]
        for (i in 1 until n) {
            dp1[i] = if (dp1[i - 1] > 0) dp1[i - 1] + arr[i] else arr[i]
            max = maxOf(max, dp1[i])
        }
        for (i in n - 2 downTo 0) {
            dp2[i] = if (dp2[i + 1] > 0) dp2[i + 1] + arr[i] else arr[i]
        }
        for (i in 1 until n - 1) {
            if (arr[i] < 0) {
                max = maxOf(max, dp1[i - 1] + dp2[i + 1])
            }
        }
        return max
    }
}

private fun test3() {
    println(Prob3())
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
