package problem.facebook.fb_1

/**
 * Created by randheercode
 * Date: 26/11/20
 * Time: 10:02 am
 */
class TribonacciNumber {
    fun tribonacci(n: Int): Int {
        if (n < 3) return if (n == 0) 0 else 1
        var tmp: Int
        var x = 0
        var y = 1
        var z = 1
        for (i in 3..n) {
            tmp = x + y + z
            x = y
            y = z
            z = tmp
        }
        return z
    }
}