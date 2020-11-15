package problem.lc_contest

import kotlin.math.abs


class W215 {

    class OrderedStream(val n: Int) {
        val data = Array<String>(n + 1) { "" }
        var ptr = 1
        fun insert(id: Int, value: String): List<String> {
            data[id] = value
            val result = mutableListOf<String>()
            var idx = ptr
            while (idx < n + 1 && data[idx].isNotEmpty()) {
                result.add(data[idx])
                idx++
            }
            ptr = idx
            return result
        }
    }

    // Wrong Answer
    fun closeStrings(word1: String, word2: String): Boolean {
        if (word1.length != word2.length) return false

        if (word1 == word2) return true

        val diff = IntArray(26)

        val count1 = IntArray(26)
        for (c in word1) count1[c - 'a'] += 1

        val count2 = IntArray(26)
        for (c in word2) {
            count2[c - 'a'] += 1
            diff[c - 'a'] = count1[c - 'a'] - count2[c - 'a']
        }

        if (diff.all { it == 0 }) return true

        val count = mutableMapOf<Int, Int>()

        for (d in diff) count[abs(d)] = count.getOrDefault(abs(d), 0) + 1

        return count.all { it.value == 2 }
    }

    private fun countOps(nums: IntArray, sIdx: Int, eIdx: Int, x: Int, count: Int): Int {

        if (x == 0) return count
        if (x < 0 || sIdx == nums.size || eIdx == -1 || eIdx < sIdx) return Int.MAX_VALUE

        return minOf(
                countOps(nums, sIdx + 1, eIdx, x - nums[sIdx], count + 1),
                countOps(nums, sIdx, eIdx - 1, x - nums[eIdx], count + 1))

    }

    fun minOperations(nums: IntArray, x: Int): Int {
        val result = countOps(nums, 0, nums.lastIndex, x, 0)
        return if (result == Int.MAX_VALUE) -1 else result
    }

}
