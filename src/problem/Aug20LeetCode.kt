package problem

import generateIntArray
import printArray


/**
 * Created by randheercode
 * Date: 9/8/20
 * Time: 4:25 pm
 */
class Aug20LeetCode {
    fun orangesRotting(grid: Array<IntArray>): Int {
        val iRange = grid.indices
        val jRange = grid[0].indices
        fun safeIndex(i: Int, j: Int) = i in iRange && j in jRange

        var dayTaken = 0
        var freshCount = 0
        //the value 0 representing an empty cell;
        //the value 1 representing a fresh orange;
        //the value 2 representing a rotten orange
        for (i in grid.indices) {
            for (j in grid[0].indices) {
                if (grid[i][j] == 1) freshCount++
            }
        }

        if (freshCount == 0) return 0

        fun updateRotten(i: Int, j: Int, visited: MutableSet<String>) {
            val key = "$i$j"
            if (grid[i][j] != 1 || visited.contains(key)) return
            grid[i][j] = 2
            freshCount--
            visited.add(key)
        }

        while (freshCount > 0) {
            val lastCount = freshCount
            val visited = mutableSetOf<String>()
            for (i in grid.indices) {
                for (j in grid[0].indices) {
                    if (grid[i][j] == 2 && !visited.contains("$i$j")) {
                        if (safeIndex(i - 1, j)) updateRotten(i - 1, j, visited)
                        if (safeIndex(i + 1, j)) updateRotten(i + 1, j, visited)
                        if (safeIndex(i, j + 1)) updateRotten(i, j + 1, visited)
                        if (safeIndex(i, j - 1)) updateRotten(i, j - 1, visited)
                    }
                }
            }
            if (freshCount == lastCount) return -1
            dayTaken++
        }

        return dayTaken
    }

    fun uniqueOccurrences(arr: IntArray): Boolean {
        val map = mutableMapOf<Int, Int>()
        for (a in arr) map[a] = map.getOrDefault(a, 0) + 1
        return map.values.toSet().size == map.size
    }

    fun numSpecialEquivGroups(A: Array<String>): Int {

        fun transformString(str: String): String {
            val evenBuilder = StringBuilder()
            val oddBuilder = StringBuilder()
            for (i in str.indices) {
                if (i.rem(2) == 0) evenBuilder.append(str[i])
                else oddBuilder.append(str[i])
            }
            return evenBuilder.groupingBy { it }.eachCount().toSortedMap().toString() + oddBuilder.groupingBy { it }.eachCount().toSortedMap().toString()
        }

        val maps = A.map { it to transformString(it) }.toMap()

        val result = mutableMapOf<String, MutableList<String>>()

        for (item in maps) {
            result[item.value] = result.getOrDefault(item.value, mutableListOf())
            result[item.value]?.add(item.key)
        }

        return result.count()
    }

    fun numSpecialEquivGroupsSmart(A: Array<String>): Int {
        val seen: MutableSet<String?> = mutableSetOf()
        for (S in A) {
            val count = IntArray(52)
            for (i in S.indices) count[S[i] - 'a' + 26 * (i % 2)]++
            seen.add(count.contentToString())
        }
        return seen.size
    }

    fun maxCount(m: Int, n: Int, ops: Array<IntArray>): Int {
        var iMin = m
        var jMin = n
        for (op in ops) {
            iMin = minOf(iMin, op[0])
            jMin = minOf(jMin, op[1])
        }
        return iMin.times(jMin)
    }

    fun freqAlphabets(s: String): String {
        var current = s.lastIndex
        val result = StringBuilder()
        while (current >= 0) {
            if (s[current] == '#') {
                val num = s.substring(current - 2, current).toInt()
                result.insert(0, (num.plus(96)).toChar())
                current -= 3
            } else {
                result.insert(0, (s[current].toInt().minus(48).plus(96)).toChar())
                current--
            }
        }
        return result.toString()
    }

    fun maxProduct(nums: IntArray): Int {
        var first = Int.MIN_VALUE
        var second = Int.MIN_VALUE
        for (i in nums.indices) {
            if (nums[i] > first) {
                second = first
                first = nums[i]
            } else if (nums[i] > second) {
                second = nums[i]
            }
        }
        return (first.minus(1)) * (second.minus(1))
    }

    fun titleToNumber(s: String): Int {
        fun getNumber(char: Char, idx: Int): Int {
            val no = char - 'A' + 1
            var num = 1
            if (idx > 0) num = Math.pow(26.0, idx.toDouble()).toInt()
            num *= no
            return num
        }

        val len = s.lastIndex
        var result = 0
        s.forEachIndexed { idx, char ->
            result += getNumber(char, len - idx)
        }
        return result
    }

    fun titleToNumberOptimal(s: String): Int {
        var result = 0
        s.forEach {
            result = result * 26 + (it - 'A' + 1)
        }
        return result
    }

    companion object {
        fun orangesRotting() {
            val input = generateIntArray("[[2,1,1],[1,1,0],[0,1,1]]")
            printArray(input)
            println(Aug20LeetCode().orangesRotting(input))
        }

        fun uniqueOccurrences() {
            println(Aug20LeetCode().uniqueOccurrences(intArrayOf(1, 2, 2, 1, 1, 3)))
            println(Aug20LeetCode().uniqueOccurrences(intArrayOf(1, 2)))
            println(Aug20LeetCode().uniqueOccurrences(intArrayOf(-3, 0, 1, -3, 1, 1, 1, -3, 10, 0)))
        }

        fun numSpecialEquivGroups() {
            println(Aug20LeetCode().numSpecialEquivGroups(arrayOf("abcd", "cdab", "cbad", "xyzz", "zzxy", "zzyx")))
            println(Aug20LeetCode().numSpecialEquivGroups(arrayOf("abc", "acb", "bac", "bca", "cab", "cba")))
        }

        fun numSpecialEquivGroupsSmart() {
            println(Aug20LeetCode().numSpecialEquivGroupsSmart(arrayOf("abcd", "cdab", "cbad", "xyzz", "zzxy", "zzyx")))
            println(Aug20LeetCode().numSpecialEquivGroupsSmart(arrayOf("abc", "acb", "bac", "bca", "cab", "cba")))
        }

        fun maxCount() {
            println(Aug20LeetCode().maxCount(3, 3, generateIntArray("[[2,2],[3,3]]")))
            println(Aug20LeetCode().maxCount(3, 3, arrayOf()))
        }

        fun freqAlphabets() {
            println(Aug20LeetCode().freqAlphabets("10#11#12"))
            println(Aug20LeetCode().freqAlphabets("1326#"))
        }

        fun maxProduct() {
            println(Aug20LeetCode().maxProduct(intArrayOf(3, 4, 5, 2)))
            println(Aug20LeetCode().maxProduct(intArrayOf(1, 5, 4, 5)))
            println(Aug20LeetCode().maxProduct(intArrayOf(3, 7)))
        }

        fun titleToNumber() {
            println(Aug20LeetCode().titleToNumber("A"))
            println(Aug20LeetCode().titleToNumber("AB"))
            println(Aug20LeetCode().titleToNumber("ZY"))
            println(Aug20LeetCode().titleToNumber("FXSHRXW"))
        }

        fun titleToNumberOptimal() {
            println(Aug20LeetCode().titleToNumber("A"))
            println(Aug20LeetCode().titleToNumber("AB"))
            println(Aug20LeetCode().titleToNumber("ZY"))
            println(Aug20LeetCode().titleToNumber("FXSHRXW"))
        }
    }
}

fun main() {
    Aug20LeetCode.titleToNumberOptimal()
}