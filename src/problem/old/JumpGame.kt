package problem.old

import java.util.*

/**
 * Created by randheercode
 * Date: 26/4/20
 * Time: 1:04 pm
 * Statement: Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 * Determine if you are able to reach the last index.
 *
 * Solution: The idea is keep a list of all possible index you can jump from one index. finally check if can jump to last
 * index or not.
 */
class JumpGame {
    fun canJump(nums: IntArray): Boolean {
        val set = BitSet(nums.size)
        set.set(0)
        nums.forEachIndexed { index, num ->
            if (set.get(index)) {
                for (i in index..index + num) {
                    set.set(i)
                }
            }
        }
        return set.get(nums.size - 1)
    }
}

fun main() {
    println(JumpGame().canJump(intArrayOf(2, 3, 1, 1, 4)))
    println(JumpGame().canJump(intArrayOf(3, 2, 1, 0, 4)))
    println(JumpGame().canJump(intArrayOf(1)))
}