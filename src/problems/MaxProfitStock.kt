package problems

/**
 * Created by randheercode
 * Date: 24/5/20
 * Time: 12:17 am
 * Problem Statement:
 */
class MaxProfitStock {
    fun maxProfit(prices: IntArray): Int {
        var buyMode = true
        var buyPrice = 0
        var profit = 0
        for (i in prices.indices) {
            if (buyMode && prices[i] < prices.getOrElse(i + 1) { 0 }) {
                buyPrice = prices[i]
                buyMode = false
            }

            if (!buyMode && (prices[i] > prices.getOrElse(i + 1) { 0 } || (i == prices.lastIndex && prices[i] > buyPrice))) {
                profit += prices[i].minus(buyPrice)
                buyMode = true
            }
        }
        return profit
    }

    fun maxProfitCoolDown(prices: IntArray): Int {
        if (prices.size <= 1) return 0
        if (prices.size == 2) return if (prices[0] > prices[1]) 0 else prices[1] - prices[0]
        val dp = Array(prices.size) { IntArray(2) }
        dp[0][0] = 0
        dp[0][1] = -prices[0]
        dp[1][0] = maxOf(dp[0][1] + prices[1], dp[0][0])
        dp[1][1] = maxOf(dp[0][0] - prices[1], dp[0][1])
        for (i in 2 until prices.size) {
            dp[i][0] = maxOf(dp[i - 1][1] + prices[i], dp[i - 1][0])
            dp[i][1] = maxOf(dp[i - 2][0] - prices[i], dp[i - 1][1])
        }
        return dp[prices.size - 1][0]
    }
}

fun main() {
    fun maxProfit() {
        println(MaxProfitStock().maxProfit(intArrayOf(1, 2, 3, 4, 5)))
        println(MaxProfitStock().maxProfit(intArrayOf(7, 6, 4, 3, 1)))
        println(MaxProfitStock().maxProfit(intArrayOf(1, 3, 2, 5, 7, 8, 2, 9, 3, 6, 2)))
    }

    fun maxProfitCoolDown() {
        println(MaxProfitStock().maxProfitCoolDown(intArrayOf(1, 2, 3, 0, 2)))
    }
    maxProfitCoolDown()
}