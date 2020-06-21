package ds

import java.util.*


/**
 * Created by randheercode
 * Date: 9/6/20
 * Time: 2:00 pm
 * Source: https://leetcode.com/explore/learn/card/fun-with-arrays/
 */
class Array101 {

    // https://leetcode.com/explore/learn/card/fun-with-arrays/521/introduction/3238/
    fun findMaxConsecutiveOnes(nums: IntArray): Int {
        var max = 0
        var current = 0
        for (n in nums) {
            if (n == 1) current++
            else current = 0
            if (current > max) {
                max = current
            }
        }
        return max
    }

    // https://leetcode.com/explore/learn/card/fun-with-arrays/521/introduction/3237/
    fun findNumbers(nums: IntArray): Int {
        fun digitCount(n: Int): Int {
            var n = n
            var count = 0
            while (n > 0) {
                count++
                n /= 10
            }
            return count
        }

        var evenDigitCount = 0
        for (n in nums) if (digitCount(n).rem(2) == 0) evenDigitCount++
        return evenDigitCount
    }

    // https://leetcode.com/explore/learn/card/fun-with-arrays/521/introduction/3240/
    fun sortedSquares(A: IntArray): IntArray {
        val result = IntArray(A.size)
        A.forEachIndexed { index, num -> result[index] = num * num }
        return result.sortedArray()
    }

    // https://leetcode.com/explore/learn/card/fun-with-arrays/525/inserting-items-into-an-array/3245/
    fun duplicateZeros(arr: IntArray) {
        var index = 0
        val length = arr.lastIndex
        var dups = 0

        while (index < length.minus(dups)) {
            if (arr[index] == 0) {
                dups++
            }
            index++
        }

        val last: Int = length - dups

        for (i in last downTo 0) {
            if (arr[i] == 0) {
                arr[i + dups] = 0
                dups--
                arr[i + dups] = 0
            } else {
                arr[i + dups] = arr[i]
            }
        }
    }

    fun removeElement(nums: IntArray, `val`: Int): Int {
        var newLength = -1
        for (n in nums) {
            if (n != `val`) {
                newLength++
                nums[newLength] = n
            }
        }
        return newLength + 1
    }

    fun checkIfExist(arr: IntArray): Boolean {
        val n: Int = arr.size
        val set = HashSet<Int>()
        for (i in 0 until n) {
            if (arr[i] % 2 == 0 && set.contains(arr[i] / 2) || set.contains(arr[i] * 2)) return true
            set.add(arr[i])
        }
        return false
    }

    fun validMountainArray(A: IntArray): Boolean {
        val N: Int = A.size
        var i = 0
        while (i + 1 < N && A[i] < A[i + 1]) i++
        if (i == 0 || i == N - 1) return false
        while (i + 1 < N && A[i] > A[i + 1]) i++
        return i == N - 1
    }

    fun replaceElements(arr: IntArray): IntArray {
        var index = arr.lastIndex
        if (arr.isEmpty()) return arr
        var max = -1
        while (index >= 0) {
            val current = arr[index]
            arr[index] = max
            max = maxOf(max, current)
            index--
        }
        return arr
    }

    fun sortArrayByParity(A: IntArray): IntArray {
        val ans = IntArray(A.size)
        var t = 0
        for (i in A.indices) if (A[i] % 2 == 0) ans[t++] = A[i]
        for (i in A.indices) if (A[i] % 2 == 1) ans[t++] = A[i]
        return ans
    }

    fun heightChecker(heights: IntArray): Int {
        val sorted = heights.sortedArray()
        var count = 0
        for (i in heights.indices)
            if (heights[i] != sorted[i]) count++
        return count
    }

    fun findMaxConsecutiveOnesReplacingOne(nums: IntArray): Int {
        var result = 0
        val n = nums.size
        var count = 0
        val dp = IntArray(n)
        for (i in 0 until n) {
            dp[i] = count
            if (nums[i] == 1) {
                count++
                result = maxOf(count, result)
            } else count = 0
        }
        count = 0
        for (i in n - 1 downTo 0) {
            dp[i] += count
            if (nums[i] == 1) count++ else count = 0
        }
        for (i in 0 until n) {
            if (nums[i] == 0) {
                result = maxOf(result, dp[i] + 1)
            }
        }
        return result
    }

    fun findMaxConsecutiveOnesReplacing2(nums: IntArray): Int {
        var res = 0
        var cur = 0
        var cnt = 0
        for (i in nums.indices) {
            cnt++
            if (nums[i] == 0) {
                cur = cnt
                cnt = 0
            }
            res = maxOf(res, cnt + cur)
        }
        return res
    }

    fun thirdMax(nums: IntArray): Int {
        val maximums: MutableSet<Int> = HashSet()
        for (num in nums) {
            maximums.add(num)
            if (maximums.size > 3) {
                maximums.remove(Collections.min(maximums))
            }
        }
        return if (maximums.size == 3) {
            Collections.min(maximums)
        } else Collections.max(maximums)
    }

    fun findDisappearedNumbers(nums: IntArray): List<Int>? {
        val hashTable = HashMap<Int, Boolean>()
        for (i in nums.indices) {
            hashTable[nums[i]] = true
        }
        val result: MutableList<Int> = mutableListOf()

        for (i in 1..nums.size) {
            if (!hashTable.containsKey(i)) {
                result.add(i)
            }
        }
        return result
    }

    fun isSubsequence(s: String, t: String): Boolean {
        var S = 0
        var T = 0
        var count = 0

        while (S < s.length && T < t.length) {
            if (s[S] == t[T]) {
                S++
                T++
                count++
            } else {
                T++
            }
        }

        return count == s.length

    }

}

fun main() {
    println(Array101().findMaxConsecutiveOnesReplacing2(intArrayOf(1, 0, 1, 1, 0, 1)))
}