package problem.lc_contest

import java.util.*


class W214 {

    fun getMaximumGenerated(n: Int): Int {
        val dp = IntArray(n + 1) { -1 }
        var maxNum = -1
        for (i in 0..n) {
            if (i == 0 || i == 1) {
                dp[i] = i
            } else if (i.rem(2) == 0) {
                dp[i] = dp[i / 2]
            } else {
                dp[i] = dp[i / 2] + dp[i / 2 + 1]
            }
            maxNum = maxOf(maxNum, dp[i])
        }
        return maxNum
    }

    fun minDeletions(s: String): Int {
        val count = IntArray(26)
        for (c in s) count[(c - 'a')] += 1
        val priorityQueue = PriorityQueue<Int> { a, b -> b - a }

        for (i in count.indices)
            if (count[i] != 0) priorityQueue.add(count[i])

        var delCount = 0
        while (priorityQueue.isNotEmpty()) {
            val top = priorityQueue.poll()
            if (priorityQueue.isEmpty()) {
                return delCount
            }
            if (top == priorityQueue.peek()) {
                if (top > 1) {
                    priorityQueue.add(top - 1)
                }
                delCount++
            }
        }
        return delCount
    }

    fun maxProfit(inventory: IntArray, orders: Int): Int {
        //first you can get the sum by formula, so use long instead of int
        //then problem converted to how many ball of each color we can allocate
        var orders = orders
        var res: Long = 0

        //for example orders = 10, we have balls, 5, 4, 3, 2, so value 5 has 1 count, value 4 has 2 counts
        //value 3 has 3 counts, value 2 has 4 counts, value 1 has 4 counts
        //so 1+2+3+4 = 10, so we need 5 + 2 * 4 + 3*3 + 4*2 = 30
        val mod = (1e9 + 7).toInt()
        Arrays.sort(inventory)
        var multipler: Long = 1
        val n = inventory.size
        var i = n - 1
        while (i >= 0 && orders > 0) {
            val prev = if (i == 0) 0 else inventory[i - 1]
            val diff = (inventory[i] - prev).toLong() * multipler
            if (diff >= orders) {
                val k1 = orders / multipler
                val k2 = orders % multipler
                val start = inventory[i].toLong() - k1 + 1
                res += k1 * (inventory[i] + start) / 2 * multipler
                val cur = start - 1
                res += cur * k2
                orders = 0
            } else {
                orders -= diff.toInt()
                res += multipler * (inventory[i] + inventory[i - 1] + 1).toLong() * (inventory[i] - inventory[i - 1]) / 2
            }
            multipler += 1
            i--
        }
        return (res % mod).toInt()
    }

}
