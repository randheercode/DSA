package problem.facebook.fb_1

import java.util.*


/**
 * Created by randheercode
 * Date: 4/11/20
 * Time: 4:58 pm
 */
class SparseVector(nums: IntArray) {
    var map: MutableMap<Int, Int> = HashMap()

    init {
        for (i in nums.indices) if (nums[i] != 0) map[i] = nums[i]
    }


    fun dotProduct(vec: SparseVector): Int {
        // We want to iterate through the smaller map.
        if (vec.map.size < map.size) return vec.dotProduct(this)
        var result = 0
        for (currIdx in map.keys) {
            // If both vectors have a non-zero value at currIdx then multiply the values and add them to the result.
            if (vec.map.containsKey(currIdx)) {
                result += map[currIdx]!! * vec.map[currIdx]!!
            }
        }
        return result
    }
}