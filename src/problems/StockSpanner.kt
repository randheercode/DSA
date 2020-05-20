package problems

import java.util.*


/**
 * Created by randheercode
 * Date: 20/5/20
 * Time: 10:36 am
 * Problem Statement: Write a class StockSpanner which collects daily price quotes for some stock, and returns the span of that stock's price for the current day.
 * The span of the stock's price today is defined as the maximum number of consecutive days (starting from today and going backwards) for which the price of the stock was less than or equal to today's price.
 * For example, if the price of a stock over the next 7 days were [100, 80, 60, 70, 60, 75, 85], then the stock spans would be [1, 1, 1, 2, 1, 4, 6].
 */
class StockSpanner {

    private var s: Stack<IntArray> = Stack()

    fun next(price: Int): Int {
        var span = 1
        while (!s.isEmpty() && price >= s.peek()[0]) {
            span += s.peek().get(1)
            s.pop()
        }
        s.push(intArrayOf(price, span))
        return span
    }

}

fun main() {
    val S = StockSpanner()
    S.next(100)
    S.next(80)
    S.next(60)
    S.next(70)
    S.next(60)
    S.next(75)
    S.next(85)
}