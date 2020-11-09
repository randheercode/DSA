package problem.facebook.fb_1

/**
 * Created by randheercode
 * Date: 9/11/20
 * Time: 10:48 pm
 */
class PowXndN {
    private fun fastPow(x: Double, n: Long): Double {
        if (n == 0L) {
            return 1.0
        }
        val half = fastPow(x, n / 2)
        return if (n % 2 == 0L) {
            half * half
        } else {
            half * half * x
        }
    }

    fun myPow(x: Double, n: Int): Double {
        var x = x
        var N = n.toLong()
        if (N < 0) {
            x = 1 / x
            N = -N
        }
        return fastPow(x, N)
    }
}