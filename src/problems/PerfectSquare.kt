package problems

/**
 * Created by randheercode
 * Date: 9/5/20
 * Time: 8:27 pm
 */
class PerfectSquare {
    fun isPerfectSquare(num: Int): Boolean {
        var start = 1
        var end = num
        while (start <= end) {
            val mid = start + (end - start) / 2.toLong()
            when {
                mid * mid == num.toLong() -> return true
                mid * mid > num -> end = (mid - 1).toInt()
                else -> start = (mid + 1).toInt()
            }
        }
        return false
    }
}

fun main() {
    println(PerfectSquare().isPerfectSquare(9))
    println(PerfectSquare().isPerfectSquare(11))
    println(PerfectSquare().isPerfectSquare(144))
    println(PerfectSquare().isPerfectSquare(10000))
    println(PerfectSquare().isPerfectSquare(808201))
}