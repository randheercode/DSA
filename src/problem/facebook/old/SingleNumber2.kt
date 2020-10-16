package problem.facebook.old

import java.util.*


// https://leetcode.com/problems/single-number-ii/
class SingleNumber2 {

    // https://medium.com/@lenchen/leetcode-137-single-number-ii-31af98b0f462
    fun singleNumber(nums: IntArray): Int {
        var seenOnce = 0
        var seenTwice = 0

        for (num in nums) {
            seenOnce = seenTwice.inv() and (seenOnce xor num)
            seenTwice = seenOnce.inv() and (seenTwice xor num)
        }

        return seenOnce
    }

    fun singleNumberHashMap(nums: IntArray): Int {
        val hashmap = mutableMapOf<Int, Int>()
        for (num in nums) hashmap[num] = hashmap.getOrDefault(num, 0) + 1
        for (k in hashmap.keys) if (hashmap[k] == 1) return k
        return -1
    }

    // 3×(a+b+c)−(a+a+a+b+b+b+c)=2c
    fun singleNumberHashSet(nums: IntArray): Int {
        val set: MutableSet<Long> = HashSet()
        var sumSet: Long = 0
        var sumArray: Long = 0
        for (n in nums) {
            sumArray += n.toLong()
            set.add(n.toLong())
        }
        for (s in set) sumSet += s
        return ((3 * sumSet - sumArray) / 2).toInt()
    }
}

fun main() {
    println(SingleNumber2().singleNumber(intArrayOf(2, 2, 3, 2)))
    println(SingleNumber2().singleNumber(intArrayOf(0, 1, 0, 1, 0, 1, 99)))
    println(SingleNumber2().singleNumberHashMap(intArrayOf(2, 2, 3, 2)))
    println(SingleNumber2().singleNumberHashMap(intArrayOf(0, 1, 0, 1, 0, 1, 99)))
    println(SingleNumber2().singleNumberHashSet(intArrayOf(2, 2, 3, 2)))
    println(SingleNumber2().singleNumberHashSet(intArrayOf(0, 1, 0, 1, 0, 1, 99)))
}