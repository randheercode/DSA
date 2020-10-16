package problem.facebook.old


// https://leetcode.com/problems/single-number-iii/
class SingleNumber3 {

    // https://leetcode.com/problems/single-number-iii/solution/
    fun singleNumber(nums: IntArray): IntArray {
        var bitmask = 0
        for (num in nums) bitmask = bitmask xor num
        val diff = bitmask and -bitmask
        var x = 0
        for (num in nums) if (num and diff != 0) x = x xor num
        return intArrayOf(x, bitmask xor x)
    }

    fun singleNumberHashMap(nums: IntArray): IntArray {
        val hashmap = mutableMapOf<Int, Int>()
        for (n in nums) hashmap[n] = hashmap.getOrDefault(n, 0) + 1
        val output = IntArray(2)
        var idx = 0
        for ((key, value) in hashmap) if (value == 1) output[idx++] = key
        return output
    }

}

fun main() {
    println(SingleNumber3().singleNumber(intArrayOf(1, 2, 1, 3, 2, 5)).toList())
    println(SingleNumber3().singleNumberHashMap(intArrayOf(1, 2, 1, 3, 2, 5)).toList())
}