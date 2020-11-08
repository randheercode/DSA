package problem.facebook.fb_1

import java.util.*

/**
 * Created by randheercode
 * Date: 8/11/20
 * Time: 1:06 pm
 */
internal class RandomPickIndex(val nums: IntArray) {

    private val rand: Random = Random()

    fun pick(target: Int): Int {
        val n = nums.size
        var count = 0
        var idx = 0
        for (i in 0 until n) {
            if (nums[i] == target) {
                count++
                if (rand.nextInt(count) == 0) {
                    idx = i
                }
            }
        }
        return idx
    }

}