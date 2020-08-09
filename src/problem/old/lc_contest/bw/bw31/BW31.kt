package problem.old.lc_contest.bw.bw31

/**
 * Created by randheercode
 * LeetCode Contest.
 */
private class Prob1 {
    fun countOdds(low: Int, high: Int): Int {
        var count = high.minus(low).div(2)
        if (low.rem(2) != 0 && high.rem(2) != 0) count++
        else if (low.rem(2) != 0) count++
        else if (high.rem(2) != 0) count++
        return count
    }
}

private fun test1() {
    println(Prob1().countOdds(3, 7))
    println(Prob1().countOdds(8, 10))
    println(Prob1().countOdds(1, 10))
    println(Prob1().countOdds(1, 11))
}

private class Prob2 {

    fun numOfSubarrays(arr: IntArray): Int {
        val module = Math.pow(10.0, 9.0).toInt().plus(7)
        var odd: Int = 0
        var even: Int = 0
        var result: Int = 0
        for (i in arr) {
            if (i % 2 == 0) {
                even = even.plus(1).rem(module)
            } else {
                val temp = even
                even = odd
                odd = temp.plus(1).rem(module)
            }
            result = result.plus(odd).rem(module)
        }
        return result
    }
}

private fun test2() {
    println(Prob2().numOfSubarrays(intArrayOf(1, 3, 5)))
    println(Prob2().numOfSubarrays(intArrayOf(2, 4, 6)))
    println(Prob2().numOfSubarrays(intArrayOf(1, 2, 3, 4, 5, 6, 7)))
    println(Prob2().numOfSubarrays(intArrayOf(100, 100, 99, 99)))
    println(Prob2().numOfSubarrays(intArrayOf(7)))
}

private class Prob3 {
    fun numSplits(s: String): Int {
        var good = 0
        val rightCount = s.groupingBy { it }.eachCount().toMutableMap()
        val leftCount = mutableMapOf<Char, Int>()
        for (i in s.indices) {
            val char = s[i]
            val newCount = rightCount[char]!!.minus(1)
            if (newCount == 0) rightCount.remove(char) else rightCount[char] = newCount
            leftCount[char] = leftCount.getOrDefault(char, 0).plus(1)
            if (leftCount.size == rightCount.size) good++
        }
        return good
    }
}

private fun test3() {
    println(Prob3().numSplits("aacaba"))
    println(Prob3().numSplits("abcd"))
    println(Prob3().numSplits("aaaaa"))
    println(Prob3().numSplits("acbadbaada"))
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
