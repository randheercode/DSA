package problem.facebook.fb_1

/**
 * Created by randheercode
 * Date: 6/11/20
 * Time: 3:29 pm
 */
class RandomPickWithWeight(w: IntArray) {
    private lateinit var prefixSums: IntArray
    private var totalSum = 0

    init {
        solution(w)
    }

    private fun solution(w: IntArray) {
        prefixSums = IntArray(w.size)
        var prefixSum = 0
        for (i in w.indices) {
            prefixSum += w[i]
            prefixSums[i] = prefixSum
        }
        totalSum = prefixSum
    }

    fun pickIndex(): Int {
        val target = totalSum * Math.random()

        // run a binary search to find the target zone
        var low = 0
        var high = prefixSums.size
        while (low < high) {
            // better to avoid the overflow
            val mid = low + (high - low) / 2
            if (target > prefixSums[mid]) low = mid + 1 else high = mid
        }
        return low
    }
}