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
}

fun main() {
    println(MaxProfitStock().maxProfit(intArrayOf(1, 2, 3, 4, 5)))
    println(MaxProfitStock().maxProfit(intArrayOf(7, 6, 4, 3, 1)))
    println(MaxProfitStock().maxProfit(intArrayOf(1, 3, 2, 5, 7, 8, 2, 9, 3, 6, 2)))
}