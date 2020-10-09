package problem.facebook

// https://leetcode.com/problems/integer-to-roman/
class IntegerToRoman {
    var values = intArrayOf(1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1)
    var symbols = arrayOf("M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I")

    fun intToRoman(num: Int): String? {
        var n = num
        val sb = StringBuilder()
        var i = 0
        while (i < values.size && n >= 0) {
            while (values[i] <= n) {
                n -= values[i]
                sb.append(symbols[i])
            }
            i++
        }
        return sb.toString()
    }
}

fun main() {
    println(IntegerToRoman().intToRoman(3))
    println(IntegerToRoman().intToRoman(4))
    println(IntegerToRoman().intToRoman(9))
    println(IntegerToRoman().intToRoman(58))
    println(IntegerToRoman().intToRoman(1994))
}