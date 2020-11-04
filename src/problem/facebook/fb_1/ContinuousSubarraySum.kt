package problem.facebook.fb_1

import java.util.*


/**
 * Created by randheercode
 * Date: 4/11/20
 * Time: 4:48 pm
 */
class ContinuousSubarraySum {
    fun checkSubarraySum(nums: IntArray, k: Int): Boolean {
        val sum = IntArray(nums.size)
        sum[0] = nums[0]
        for (i in 1 until nums.size) sum[i] = sum[i - 1] + nums[i]
        for (start in 0 until nums.size - 1) {
            for (end in start + 1 until nums.size) {
                val summ = sum[end] - sum[start] + nums[start]
                if (summ == k || k != 0 && summ % k == 0) return true
            }
        }
        return false
    }

    fun checkSubarraySumOptimal(nums: IntArray, k: Int): Boolean {
        var sum = 0
        val map = HashMap<Int, Int>()
        map[0] = -1
        for (i in nums.indices) {
            sum += nums[i]
            if (k != 0) sum %= k
            if (map.containsKey(sum)) {
                if (i - map[sum]!! > 1) return true
            } else map[sum] = i
        }
        return false
    }
}