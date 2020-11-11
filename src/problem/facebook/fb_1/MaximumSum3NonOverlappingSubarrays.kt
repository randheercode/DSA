package problem.facebook.fb_1

/**
 * Created by randheercode
 * Date: 11/11/20
 * Time: 9:29 am
 */
class MaximumSum3NonOverlappingSubarrays {
    fun maxSumOfThreeSubarrays(nums: IntArray, k: Int): IntArray? {
        val n = nums.size
        val m = 3
        val sums = IntArray(n + 1)
        val max = Array(n + 1) { IntArray(m + 1) }
        val indices = Array(n + 1) { IntArray(m + 1) }
        for (i in 1..n) sums[i] = sums[i - 1] + nums[i - 1]
        for (i in 1..m) {
            for (j in i * k..n) {
                if (max[j - k][i - 1] + sums[j] - sums[j - k] > max[j - 1][i]) {
                    indices[j][i] = j - k
                    max[j][i] = max[j - k][i - 1] + sums[j] - sums[j - k]
                } else {
                    max[j][i] = max[j - 1][i]
                    indices[j][i] = indices[j - 1][i]
                }
            }
        }
        val ret = IntArray(m)
        ret[m - 1] = indices[n][m]
        for (i in m - 2 downTo 0) ret[i] = indices[ret[i + 1]][i + 1]
        return ret
    }
}