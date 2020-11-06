package problem.facebook.fb_1

/**
 * Created by randheercode
 * Date: 6/11/20
 * Time: 3:24 pm
 */
class DivideTwoIntegers {
    private val HALF_INT_MIN = -1073741824
    fun divide(dividend: Int, divisor: Int): Int {
        var dividend = dividend
        var divisor = divisor
        if (dividend == Int.MIN_VALUE && divisor == -1) {
            return Int.MAX_VALUE
        }
        var negatives = 2
        if (dividend > 0) {
            negatives--
            dividend = -dividend
        }
        if (divisor > 0) {
            negatives--
            divisor = -divisor
        }
        var quotient = 0
        while (divisor >= dividend) {
            var powerOfTwo = -1
            var value = divisor
            while (value >= HALF_INT_MIN && value + value >= dividend) {
                value += value
                powerOfTwo += powerOfTwo
            }
            quotient += powerOfTwo
            dividend -= value
        }
        return if (negatives != 1) {
            -quotient
        } else quotient
    }
}