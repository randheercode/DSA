package problem.lc_contest.w

/**
 * Created by randheercode
 * LeetCode Contest.
 */
private class Prob1 {
    fun mostVisited(n: Int, rounds: IntArray): List<Int> {
        val trackRecord = IntArray(n)
        trackRecord[rounds[0] - 1] = 1
        for (i in 0 until rounds.lastIndex) {
            val start = rounds[i]
            var end = rounds[i + 1] - 1
            if (end < start) end += n
            for (j in start..end) {
                trackRecord[j.rem(n)] += 1
            }
        }
        val result = mutableListOf<Int>()
        val max = trackRecord.max()!!
        trackRecord.forEachIndexed { index, i ->
            if (i == max) result.add(index + 1)
        }
        return result
    }
}

private fun test1() {
    println(Prob1().mostVisited(4, intArrayOf(1, 3, 1, 2)))
    println(Prob1().mostVisited(2, intArrayOf(2, 1, 2, 1, 2, 1, 2, 1, 2)))
    println(Prob1().mostVisited(7, intArrayOf(1, 3, 5, 7)))
}

private class Prob2 {
    fun maxCoins(piles: IntArray): Int {
        piles.sort()
        var result = 0
        val step = piles.size.div(3)
        var cStep = 1
        var idx = piles.lastIndex - 1
        while (cStep <= step) {
            result += piles[idx]
            idx -= 2
            cStep++
        }
        return result
    }
}

private fun test2() {
    println(Prob2().maxCoins(intArrayOf(2, 4, 1, 2, 7, 8)))
    println(Prob2().maxCoins(intArrayOf(2, 4, 5)))
    println(Prob2().maxCoins(intArrayOf(9, 8, 7, 6, 5, 1, 2, 3, 4)))
    println(Prob2().maxCoins(intArrayOf(9, 8, 7, 6, 5, 1, 2, 3, 4)))
}

private class Prob3 {
    fun findLatestStep(A: IntArray, m: Int): Int {
        var res = -1
        val n = A.size
        val length = IntArray(n + 2)
        val count = IntArray(n + 1)
        for (i in 0 until n) {
            val a = A[i]
            val left = length[a - 1]
            val right = length[a + 1]
            length[a + right] = left + right + 1
            length[a - left] = length[a + right]
            length[a] = length[a - left]
            count[left]--
            count[right]--
            count[length[a]]++
            if (count[m] > 0) res = i + 1
        }
        return res
    }
}

private fun test3() {
    println(Prob3().findLatestStep(intArrayOf(3, 5, 1, 2, 4), 1))
    println(Prob3().findLatestStep(intArrayOf(3, 1, 5, 4, 2), 2))
    println(Prob3().findLatestStep(intArrayOf(1), 1))
    println(Prob3().findLatestStep(intArrayOf(2, 1), 2))
    println(Prob3().findLatestStep(intArrayOf(10, 6, 9, 4, 7, 8, 5, 2, 1, 3), 1))
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
