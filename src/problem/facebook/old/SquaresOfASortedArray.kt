package problem.facebook.old

import kotlin.math.abs

// https://leetcode.com/problems/squares-of-a-sorted-array/
class SquaresOfASortedArray {
    fun sortedSquares(A: IntArray): IntArray {
        var left = 0
        var right = A.lastIndex
        var idx = A.lastIndex
        val result = IntArray(A.size) { 0 }
        while (left <= right) {
            val l = abs(A[left])
            val r = abs(A[right])
            result[idx] = if (l >= r) {
                left++
                l * l
            } else {
                right--
                r * r
            }
            idx--
        }
        return result
    }
}

fun main() {
    println(SquaresOfASortedArray().sortedSquares(intArrayOf(-4, -1, 0, 3, 10)).toList())
    println(SquaresOfASortedArray().sortedSquares(intArrayOf(-7, -3, 2, 3, 11)).toList())
}