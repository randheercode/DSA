package problems

/**
 * Created by randheercode
 * Date: 26/4/20
 * Time: 1:27 pm
 * Statement: Given a range [m, n] where 0 <= m <= n <= 2147483647,
 * return the bitwise AND of all numbers in this range, inclusive.
 * Solution: https://www.geeksforgeeks.org/bitwise-and-or-of-a-range/
 */
class BitwiseRangeOfNumber {
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
}