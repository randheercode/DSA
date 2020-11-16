package problem.facebook.fb_1

/**
 * Created by randheercode
 * Date: 16/11/20
 * Time: 12:05 pm
 */
class MonotonicArray {
    fun isMonotonic(A: IntArray): Boolean {
        var increasing = true
        var decreasing = true
        for (i in 0 until A.size - 1) {
            if (A[i] > A[i + 1]) increasing = false
            if (A[i] < A[i + 1]) decreasing = false
        }
        return increasing || decreasing
    }
}