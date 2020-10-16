package problem.facebook.old

// https://leetcode.com/problems/make-two-arrays-equal-by-reversing-sub-arrays/
class MakeTwoArraysEqualByReversingSubArrays {

    private fun swapSubArray(arr: IntArray, l: Int, r: Int) {
        var from = l
        var to = r
        while (from < to) {
            arr[from] = arr[from] + arr[to]
            arr[to] = arr[from] - arr[to]
            arr[from] = arr[from] - arr[to]
            from++
            to--
        }
    }

    fun canBeEqual(target: IntArray, arr: IntArray): Boolean {
        if (target.size != arr.size) return false
        val size = target.size
        var idx = 0

        while (idx < arr.size) {
            if (target[idx] != arr[idx]) {
                var tempIdx = idx
                while (tempIdx < arr.lastIndex && target[idx] != arr[tempIdx]) {
                    tempIdx++
                }
                if (target[idx] != arr[tempIdx]) return false
                swapSubArray(arr, idx, tempIdx)
            }
            idx++
        }
        return true
    }

    fun canBeEqualOptimal(target: IntArray, arr: IntArray): Boolean {
        if (target.size != arr.size) return false
        val map = mutableMapOf<Int, Int>()
        target.forEach { map[it] = map.getOrDefault(it, 0) + 1 }
        arr.forEach { map[it] = map.getOrDefault(it, 0) - 1 }
        return map.filter { it.value > 0 }.isEmpty()
    }
}

fun main() {
    println(MakeTwoArraysEqualByReversingSubArrays().canBeEqual(
            intArrayOf(1, 2, 3, 4), intArrayOf(2, 4, 1, 3)
    ))
    println(MakeTwoArraysEqualByReversingSubArrays().canBeEqual(
            intArrayOf(7), intArrayOf(7)
    ))
    println(MakeTwoArraysEqualByReversingSubArrays().canBeEqual(
            intArrayOf(1, 12), intArrayOf(12, 1)
    ))
    println(MakeTwoArraysEqualByReversingSubArrays().canBeEqual(
            intArrayOf(3, 7, 9), intArrayOf(3, 7, 11)
    ))
    println(MakeTwoArraysEqualByReversingSubArrays().canBeEqualOptimal(
            intArrayOf(1, 2, 3, 4), intArrayOf(2, 4, 1, 3)
    ))
    println(MakeTwoArraysEqualByReversingSubArrays().canBeEqualOptimal(
            intArrayOf(7), intArrayOf(7)
    ))
    println(MakeTwoArraysEqualByReversingSubArrays().canBeEqualOptimal(
            intArrayOf(1, 12), intArrayOf(12, 1)
    ))
    println(MakeTwoArraysEqualByReversingSubArrays().canBeEqualOptimal(
            intArrayOf(3, 7, 9), intArrayOf(3, 7, 11)
    ))
}