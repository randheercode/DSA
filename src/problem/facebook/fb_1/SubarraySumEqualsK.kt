package problem.facebook.fb_1

import java.util.*


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

    fun subarraySumOptimal(nums: IntArray, k: Int): Int {
        var count = 0
        var sum = 0
        val map = HashMap<Int, Int?>()
        map[0] = 1
        for (i in nums.indices) {
            sum += nums[i]
            if (map.containsKey(sum - k)) count += map[sum - k]!!
            map[sum] = map.getOrDefault(sum, 0)!! + 1
        }
        return count
    }
}