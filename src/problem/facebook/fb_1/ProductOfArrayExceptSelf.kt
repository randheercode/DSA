package problem.facebook.fb_1

/**
 * Created by randheercode
 * Date: 1/11/20
 * Time: 10:04 pm
 */
class ProductOfArrayExceptSelf {

    fun productExceptSelf(nums: IntArray): IntArray {
        val length = nums.size
        val L = IntArray(length)
        val R = IntArray(length)
        val answer = IntArray(length)
        L[0] = 1
        for (i in 1 until length) {
            L[i] = nums[i - 1] * L[i - 1]
        }
        R[length - 1] = 1
        for (i in length - 2 downTo 0) {
            R[i] = nums[i + 1] * R[i + 1]
        }
        for (i in 0 until length) {
            answer[i] = L[i] * R[i]
        }
        return answer
    }

    fun productExceptSelfOptimal(nums: IntArray): IntArray {
        val length = nums.size
        val answer = IntArray(length)
        answer[0] = 1
        for (i in 1 until length) {
            answer[i] = nums[i - 1] * answer[i - 1]
        }
        var R = 1
        for (i in length - 1 downTo 0) {
            answer[i] = answer[i] * R
            R *= nums[i]
        }
        return answer
    }
}