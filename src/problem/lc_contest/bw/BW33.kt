package problem.lc_contest.bw

/**
 * Created by randheercode
 * LeetCode Contest.
 */
private class Prob1 {
    fun thousandSeparator(n: Int): String {
        val str = n.toString()
        val result = StringBuilder()
        var idx = str.length
        var places = 0
        while (idx > 0) {
            result.insert(0, str[idx - 1])
            idx--
            places++
            if (places == 3 && idx != 0) {
                result.insert(0, ".")
                places = 0
            }
        }
        return result.toString()
    }
}

private fun test1() {
    println(Prob1().thousandSeparator(0))
    println(Prob1().thousandSeparator(999))
    println(Prob1().thousandSeparator(1234))
    println(Prob1().thousandSeparator(123456789))
    println(Prob1().thousandSeparator(51040))
}

private class Prob2 {

}

private fun test2() {
    println(Prob2())
}

private class Prob3 {
    fun minOperations(nums: IntArray): Int {
        if (nums.contains(0)) return nums.sum()
        val min = nums.min()!!
        var tempMin = min
        var fCall = nums.size
        if (min.rem(2) != 0 && min > 1) {
            fCall += nums.size
            tempMin -= 1
        }
        while (tempMin > 1) {
            tempMin /= 2
            fCall += 1
        }
        nums.forEach {
            fCall += it.minus(min)
        }
        return fCall
    }
}

private fun test3() {
    println(Prob3().minOperations(intArrayOf(1, 5)))
    println(Prob3().minOperations(intArrayOf(2, 2)))
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
