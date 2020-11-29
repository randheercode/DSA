package problem.lc_contest

import kotlin.math.abs


class W217 {

    fun maximumWealth(accounts: Array<IntArray>): Int {
        return accounts.map { it.sum() }.max()!!
    }

    fun mostCompetitive(nums: IntArray, k: Int): IntArray {
        if (nums.isEmpty() || k == nums.size) return nums
        val result = mutableListOf<Int>()
        var count = k

        var lastIdx = -1
        val size = nums.size

        fun findDigit(left: Int): Int {
            var idx = -1
            var num = Int.MAX_VALUE
            for (i in lastIdx.plus(1)..(size - left)) {
                if (nums[i] < num) {
                    idx = i
                    num = nums[i]
                }
            }
            lastIdx = idx
            return num
        }

        while (count > 0) {
            result.add(findDigit(count))
            count--
        }

        return result.toIntArray()
    }

    fun minMoves(nums: IntArray, limit: Int): Int {
        if (nums.isEmpty()) return 0
        var moves = 0
        val n = nums.size
        val prep = (0 until n.div(2)).map { i -> nums[i] + nums[n - 1 - i] }.sortedDescending()
        val target = prep.groupingBy { it }.eachCount().filter { it.key < limit * 2 }.maxBy { it.value }?.key ?: abs(nums.first().minus(nums.last()))


        for (i in 0 until n.div(2)) {
            val first = nums[i]
            val last = nums[n - i - 1]
            if (first + last == target) continue
            if (first + last != target) {
                if (abs(target - first - last) < limit) {
                    moves++
                } else {
                    moves += 2
                }
            }
        }

        return moves

    }
}
