package problem.facebook

// https://leetcode.com/problems/integer-to-roman/
class IntegerToRoman {
    fun intToRoman(num: Int): String {
        val result = StringBuilder()
        result.insert(4,'0')
        return result.toString()
    }
}

fun main() {
    println(IntegerToRoman().intToRoman(3))
//    println(IntegerToRoman().intToRoman(4))
//    println(IntegerToRoman().intToRoman(9))
//    println(IntegerToRoman().intToRoman(58))
//    println(IntegerToRoman().intToRoman(1994))
}