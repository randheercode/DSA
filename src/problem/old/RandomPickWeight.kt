package problem.old

import java.util.*

/**
 * Created by randheercode
 * Date: 5/6/20
 * Time: 3:33 pm
 * https://leetcode.com/problems/random-pick-with-weight/
 */
class RandomPickWeight(val w: IntArray) {

    private val prefSum = IntArray(w.size)
    private var totalSum = 0
    private val random = Random()

    init {
        var sum = 0
        for (i in 0 until w.size) {
            sum += w[i]
            prefSum[i] = sum
        }

        totalSum = sum
    }

    fun pickIndex(): Int {
        val target = Math.random() * totalSum
        var left = 0
        var right = prefSum.size
        while (left < right) {
            val mid = left + (right - left) / 2
            if (target > prefSum[mid]) {
                left = mid + 1
            } else right = mid
        }

        return left
    }

}