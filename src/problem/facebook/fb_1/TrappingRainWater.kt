package problem.facebook.fb_1

/**
 * Created by randheercode
 * Date: 13/11/20
 * Time: 10:57 am
 */
class TrappingRainWater {
    fun trap(height: IntArray): Int {
        if (height.isEmpty()) return 0
        var left = 0
        var right = height.size - 1
        var leftMax = 0
        var rightMax = 0
        var ans = 0
        while (left < right) {
            if (height[left] > leftMax) leftMax = height[left]
            if (height[right] > rightMax) rightMax = height[right]
            if (leftMax < rightMax) {
                ans += maxOf(0, leftMax - height[left])
                left++
            } else {
                ans += maxOf(0, rightMax - height[right])
                right--
            }
        }
        return ans
    }
}