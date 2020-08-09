package problem.old


/**
 * Created by randheercode
 * Date: 24/6/20
 * Time: 10:41 pm
 */
class ClosestTarget {
    fun findBestValue(arr: IntArray, target: Int): Int {
        arr.sort()
        val len: Int = arr.size
        var mid = 0
        var minDiff = Int.MAX_VALUE
        var ans = 0
        var temp = 0
        var high = arr[len - 1]
        var low = 1
        if (target < len) return 0
        while (low <= high) {
            mid = (low + high) / 2
            for (i in 0 until len) {
                if (arr[i] <= mid) temp += arr[i] else {
                    temp += mid * (len - i)
                    break
                }
            }
            if (minDiff > Math.abs(target - temp)) {
                minDiff = Math.abs(target - temp)
                ans = mid
            }
            if (temp == target || low == high) return ans
            if (temp > target) high = mid else low = mid + 1
            temp = 0
        }
        return ans
    }
}