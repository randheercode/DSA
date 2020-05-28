package problems

/**
 * Created by randheercode
 * Date: 28/5/20
 * Time: 8:11 pm
 * Problem Statement: Given a non negative integer number num. For every numbers i
 * in the range 0 ≤ i ≤ num calculate the number of 1's in their binary representation and return them as an array.
 */
class CountingBits {
    fun countBits(num: Int): IntArray {
        val result = IntArray(num + 1)
        result[0] = 0
        var index = 0
        var currentRange = 1
        var nextRange = 2
        while (index < num) {
            index++
            if (nextRange == index) {
                currentRange = nextRange
                nextRange = nextRange shl 1
            }
            result[index] = 1 + result[index - currentRange]
        }
        return result
    }
}

fun main() {
    println(CountingBits().countBits(2).toList())
    println(CountingBits().countBits(5).toList())
    println(CountingBits().countBits(16).toList())
}