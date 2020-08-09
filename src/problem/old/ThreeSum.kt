package problem.old


/**
 * Created by randheercode
 * Date: 8/7/20
 * Time: 7:10 pm
 */
class ThreeSum {
    fun threeSum(nums: IntArray): List<List<Int>> {
        val result = mutableSetOf<List<Int>>()
        nums.sort()
        val len = nums.size
        for (i in 0 until len) {
            var j = i + 1
            var k: Int = len - 1
            while (j < k) {
                val sum = nums[i] + nums[j] + nums[k]
                if (sum == 0) result.add(listOf(nums[i], nums[j++], nums[k--])) else if (sum > 0) k-- else if (sum < 0) j++
            }
        }
        return result.toList()
    }
}