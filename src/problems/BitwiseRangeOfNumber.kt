package problems

/**
 * Created by randheercode
 * Date: 26/4/20
 * Time: 1:27 pm
 * Statement: Given a range [m, n] where 0 <= m <= n <= 2147483647,
 * return the bitwise AND of all numbers in this range, inclusive.
 * Solution: https://www.geeksforgeeks.org/bitwise-and-or-of-a-range/
 * SolutionAnother: Check from msb for common number and that's the answer.
 */
class BitwiseRangeOfNumber {

    fun rangeBitwiseAndAnother(m: Int, n: Int): Int {
        var mString = Integer.toBinaryString(m)
        val nString = Integer.toBinaryString(n)
        while (mString.length != nString.length) {
            mString = "0$mString"
        }
        var mIndex = 0
        var nIndex = 0
        var result = "0"
        while (mIndex < mString.length && nIndex < nString.length && mString[mIndex] == nString[nIndex]) {
            result += mString[mIndex]
            mIndex += 1
            nIndex += 1
        }
        while (nIndex < nString.length) {
            result += "0"
            nIndex += 1
        }
        return result.toInt(2)
    }

    fun rangeBitwiseAnd(m: Int, n: Int): Int {
        var b = n
        while (m < b) {
            b -= b and -b
        }
        return b
    }
}

fun main() {
    println(BitwiseRangeOfNumber().rangeBitwiseAnd(5, 7))
    println(BitwiseRangeOfNumber().rangeBitwiseAnd(0, 1))
    println(BitwiseRangeOfNumber().rangeBitwiseAnd(4000000, 2147483646))

    println(BitwiseRangeOfNumber().rangeBitwiseAndAnother(5, 7))
    println(BitwiseRangeOfNumber().rangeBitwiseAndAnother(0, 1))
    println(BitwiseRangeOfNumber().rangeBitwiseAndAnother(4000000, 2147483646))
}