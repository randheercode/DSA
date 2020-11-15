package problem.lc_contest

import java.util.*


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
        val N = 26
        val arr1 = IntArray(N)
        val arr2 = IntArray(N)
        for (ch in word1.toCharArray()) {
            arr1[ch - 'a']++
        }
        for (ch in word2.toCharArray()) {
            arr2[ch - 'a']++
        }
        for (i in 0 until N) {
            if (arr1[i] == arr2[i]) {
                continue
            }
            if (arr1[i] == 0 || arr2[i] == 0) {
                return false
            }
        }
        Arrays.sort(arr1)
        Arrays.sort(arr2)
        for (i in 0 until N) {
            if (arr1[i] != arr2[i]) {
                return false
            }
        }
        return true
    }


    fun minOperations(nums: IntArray, x: Int): Int {
        val len = nums.size
        val left = IntArray(len)
        val right = IntArray(len)
        var tmp = 0
        for (i in 0 until len) {
            tmp += nums[i]
            left[i] = tmp
        }
        tmp = 0
        for (i in len - 1 downTo -1 + 1) {
            tmp += nums[i]
            right[i] = tmp
        }
        val map: MutableMap<Int, Int?> = HashMap()
        map[x] = -1
        var res = Int.MAX_VALUE
        for (i in 0 until len) {
            if (left[i] > x) break
            if (left[i] == x) res = i + 1
            map[x - left[i]] = i
        }
        for (i in len - 1 downTo -1 + 1) {
            if (map.containsKey(right[i])) {
                res = Math.min(res, map[right[i]]!! + 1 + (len - i))
            }
        }
        return if (res == Int.MAX_VALUE || res > len) -1 else res
    }

}
