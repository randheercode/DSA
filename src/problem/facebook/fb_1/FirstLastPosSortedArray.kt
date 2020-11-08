package problem.facebook.fb_1

/**
 * Created by randheercode
 * Date: 8/11/20
 * Time: 12:58 pm
 */
class FirstLastPosSortedArray {
    private fun binarySearch(nums: IntArray, target: Int): Int {
        var left = 0
        var right = nums.lastIndex
        while (left <= right) {
            val mid = left + (right - left) / 2
            when {
                nums[mid] == target -> return mid
                nums[mid] > target -> {
                    right = mid - 1
                }
                else -> {
                    left = mid + 1
                }
            }
        }
        return -1
    }

    fun searchRange(nums: IntArray, target: Int): IntArray {
        val result = IntArray(2) { -1 }
        val pos = binarySearch(nums, target)
        if (pos == -1) return result
        var i = pos
        while (i >= 0 && nums[i] == target) {
            result[0] = i
            i--
        }
        i = pos
        while (i < nums.size && nums[i] == target) {
            result[1] = i
            i++
        }
        return result
    }
}