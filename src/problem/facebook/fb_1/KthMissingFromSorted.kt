package problem.facebook.fb_1

/**
 * Created by randheercode
 * Date: 14/11/20
 * Time: 11:57 am
 */
class KthMissingFromSorted {

    private fun missing(idx: Int, nums: IntArray): Int {
        return nums[idx] - nums[0] - idx
    }

    fun missingElement(nums: IntArray, k: Int): Int {
        val n = nums.size
        if (k > missing(n - 1, nums))
            return nums[n - 1] + k - missing(n - 1, nums)
        var idx = 1
        while (missing(idx, nums) < k) idx++
        return nums[idx - 1] + k - missing(idx - 1, nums)
    }
}