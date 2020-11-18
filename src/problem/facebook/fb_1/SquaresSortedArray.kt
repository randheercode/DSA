package problem.facebook.fb_1

import kotlin.math.abs

/**
 * Created by randheercode
 * Date: 18/11/20
 * Time: 11:40 am
 */
class SquaresSortedArray {
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