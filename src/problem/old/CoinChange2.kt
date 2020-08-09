package problem.old

/**
 * Created by randheercode
 * Date: 7/6/20
 * Time: 4:18 pm
 * LeetCode: Coin Change 2
 */
class CoinChange2 {
    fun change(amount: Int, coins: IntArray): Int {
        val dp = IntArray(amount + 1)
        dp[0] = 1
        for (coin in coins) {
            for (i in coin..amount) {
                dp[i] += dp[i - coin]
            }
        }
        return dp[amount]
    }
}

fun main() {
    //println(CoinChange2().change(5, intArrayOf(5, 2, 1)))
    println(CoinChange2().change(7, intArrayOf(1, 2, 10)))
}
