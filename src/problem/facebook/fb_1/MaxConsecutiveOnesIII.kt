package problem.facebook.fb_1

/**
 * Created by randheercode
 * Date: 18/11/20
 * Time: 11:34 am
 */
class MaxConsecutiveOnesIII {
    fun longestOnes(A: IntArray, K: Int): Int {
        var K = K
        var left = 0
        var right: Int = 0
        while (right < A.size) {
            if (A[right] == 0) K--
            if (K < 0) {
                if (A[left] == 0) K++
                left++
            }
            right++
        }
        return right - left
    }
}