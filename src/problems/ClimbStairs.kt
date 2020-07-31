package problems

/**
 * Created by randheercode
 * Date: 31/7/20
 * Time: 4:34 pm
 */
class ClimbStairs {
    private fun climbStairsHelper(i: Int, n: Int, memo: IntArray): Int {
        if (i > n) {
            return 0
        }
        if (i == n) {
            return 1
        }
        if (memo[i] > 0) {
            return memo[i]
        }
        memo[i] = climbStairsHelper(i + 1, n, memo) + climbStairsHelper(i + 2, n, memo)
        return memo[i]
    }

    fun climbStairs(n: Int): Int {
        val memo = IntArray(n + 1)
        return climbStairsHelper(0, n, memo)
    }
}

fun main() {
    println(ClimbStairs().climbStairs(5))
}