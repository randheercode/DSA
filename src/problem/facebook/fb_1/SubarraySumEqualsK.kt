package problem.facebook.fb_1

/**
 * Created by randheercode
 * Date: 1/11/20
 * Time: 9:44 pm
 */
class SubarraySumEqualsK {
    fun subarraySum(nums: IntArray, k: Int): Int {
        var count = 0
        for (i in nums.indices) {
            var sum = 0
            sum@ for (j in i until nums.size) {
                sum += nums[j]
                if (sum == k) count++
            }
        }
        return count
    }
}