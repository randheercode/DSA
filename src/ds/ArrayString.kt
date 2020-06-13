package ds

import java.math.BigInteger


/**
 * Created by randheercode
 * Date: 13/6/20
 * Time: 1:55 pm
 * Source: https://leetcode.com/explore/learn/card/array-and-string/
 */
class ArrayString {
    // https://leetcode.com/explore/learn/card/array-and-string/201/introduction-to-array/1144/
    fun pivotIndex(nums: IntArray): Int {
        var sum = 0
        var leftsum = 0
        for (x in nums) sum += x
        for (i in nums.indices) {
            if (leftsum == sum - leftsum - nums[i]) return i
            leftsum += nums[i]
        }
        return -1
    }

    fun dominantIndex(nums: IntArray): Int {
        if (nums.isEmpty()) return -1
        if (nums.size == 1) return 0
        var maxValue = Int.MIN_VALUE
        var maxIndex = 0
        var secondMaxValue = maxValue
        var secondMaxIndex = 0
        nums.mapIndexed { index, num ->
            if (num >= maxValue) {
                secondMaxIndex = maxIndex
                secondMaxValue = maxValue
                maxValue = num
                maxIndex = index
            } else if (num > secondMaxValue) {
                secondMaxIndex = index
                secondMaxValue = num
            }
        }
        return if (maxValue < secondMaxValue * 2) return -1 else maxIndex
    }

    fun plusOne(digits: IntArray): IntArray {
        if (digits.isEmpty()) return intArrayOf(1)
        var rem = 1
        var i = digits.lastIndex

        while (rem > 0 && i >= 0) {
            val sum = digits[i].plus(rem)
            digits[i] = sum % 10
            rem = sum / 10
            i--
        }

        return if (rem == 0) {
            digits
        } else {
            val newDigits = IntArray(digits.size.plus(1))
            newDigits[0] = rem
            digits.forEachIndexed { index, num -> newDigits[index.plus(1)] = num }
            newDigits
        }
    }

    fun findDiagonalOrder(matrix: Array<IntArray>): IntArray {
        if (matrix.isEmpty()) return intArrayOf()
        var row = 0
        var col = 0
        val maxRow = matrix.size - 1
        val maxCol = matrix[0].size - 1
        val results = mutableListOf<Int>()
        var directionDown = false

        while (true) {
            results.add(matrix[row][col])
            if (row == maxRow && col == maxCol) {
                return results.toIntArray()
            }
            if (!directionDown) {
                when {
                    row == 0 -> {
                        if (col < maxCol) {
                            col++
                        } else {
                            row++
                        }
                        directionDown = true
                    }
                    col == maxCol -> {
                        row++
                        directionDown = true
                    }
                    else -> {
                        row--
                        col++
                    }
                }

            } else {
                when {
                    col == 0 -> {
                        if (row < maxRow) {
                            row++
                        } else {
                            col++
                        }
                        directionDown = false
                    }
                    row == maxRow -> {
                        col = minOf(maxCol, col + 1)
                        directionDown = false
                    }
                    else -> {
                        row++
                        col--
                    }
                }
            }
        }
    }

    fun spiralOrder(matrix: Array<IntArray>): List<Int> {
        val result = mutableListOf<Int>()
        if (matrix.isEmpty()) return result
        var r1 = 0
        var r2: Int = matrix.size - 1
        var c1 = 0
        var c2: Int = matrix[0].size - 1
        while (r1 <= r2 && c1 <= c2) {
            for (c in c1..c2) result.add(matrix[r1][c])
            for (r in r1 + 1..r2) result.add(matrix[r][c2])
            if (r1 < r2 && c1 < c2) {
                for (c in c2 - 1 downTo c1 + 1) result.add(matrix[r2][c])
                for (r in r2 downTo r1 + 1) result.add(matrix[r][c1])
            }
            r1++
            r2--
            c1++
            c2--
        }
        return result
    }

    fun generate(numRows: Int): List<List<Int>> {
        if (numRows == 0) return emptyList()
        val result = mutableListOf<MutableList<Int>>()
        result.add(mutableListOf(1))
        if (numRows > 1) result.add(mutableListOf(1, 1))
        if (numRows == 2) return result
        for (row in 2 until numRows) {
            val rowData = mutableListOf<Int>()
            rowData.add(1)
            for (i in 1 until row) {
                rowData.add(result[row - 1][i - 1] + result[row - 1][i])
            }
            rowData.add(1)
            result.add(rowData)
        }
        return result
    }

    fun addBinary(a: String?, b: String?): String? {
        var x = BigInteger(a, 2)
        var y = BigInteger(b, 2)
        val zero = BigInteger("0", 2)
        var carry: BigInteger
        var answer: BigInteger
        while (y.compareTo(zero) != 0) {
            answer = x.xor(y)
            carry = x.and(y).shiftLeft(1)
            x = answer
            y = carry
        }
        return x.toString(2)
    }

    fun strStr(haystack: String, needle: String): Int {
        return haystack.indexOf(needle)
    }

    fun longestCommonPrefix(strs: Array<String>): String {
        if (strs.isEmpty()) return ""
        val length = strs.map { it.length }.min() ?: 0
        var ind = -1
        for (i in 0 until length) {

            if (strs.map { it[i] }.toSet().size == 1) {
                ind = i
            } else {
                break
            }

        }
        return if (ind > 0) strs[0].substring(0..ind) else ""
    }

    fun arrayPairSum(nums: IntArray): Int {
        nums.sort()
        val n = nums.size.div(2)
        var sum = 0
        for (i in 0..n) {
            sum += minOf(nums[2 * i], nums[(2 * i).plus(1)])
        }
        return sum
    }

    fun minSubArrayLen(s: Int, nums: IntArray): Int {
        if (nums.isEmpty()) {
            return 0
        }
        var minLen = Int.MAX_VALUE
        val n: Int = nums.size
        val prefixSum = IntArray(n + 1)
        prefixSum[0] = 0
        for (i in 1 until n + 1) {
            prefixSum[i] = nums[i - 1] + prefixSum[i - 1]
        }
        var l = 0
        var r = n
        while (l < r) {
            val len = (r + l) / 2
            for (i in len..n) {
                if (prefixSum[i] - prefixSum[i - len] >= s) {
                    minLen = len
                    r = len
                    break
                }
            }
            if (r != len) {
                l = len + 1
            }
        }
        for (i in r..n) {
            if (prefixSum[i] - prefixSum[i - r] >= s) {
                minLen = r
                break
            }
        }
        return if (minLen == Int.MAX_VALUE) 0 else minLen
    }

    fun rotate(nums: IntArray, k: Int) {
        val result = IntArray(nums.size)
        val length = nums.size
        val K = k.rem(length)
        //[1,2,3,4,5,6,7] => [5,6,7,1,2,3,4]
        for (i in nums.indices) {
            result[i] = nums[(length.minus(K).plus(i)).rem(length)]
        }
        for (i in nums.indices) nums[i] = result[i]
    }

    fun rotate1(nums: IntArray, k: Int) {
        var temp: Int
        var previous: Int
        for (i in 0 until k) {
            previous = nums[nums.size - 1]
            for (j in nums.indices) {
                temp = nums[j]
                nums[j] = previous
                previous = temp
            }
        }
    }

    fun rotate2(nums: IntArray, k: Int) {
        var k = k
        k %= nums.size
        var count = 0
        var start = 0
        while (count < nums.size) {
            var current = start
            var prev = nums[start]
            do {
                val next = (current + k) % nums.size
                val temp = nums[next]
                nums[next] = prev
                prev = temp
                current = next
                count++
            } while (start != current)
            start++
        }
    }


    fun getRow(n: Int): List<Int> {
        val row: MutableList<Int> = mutableListOf<Int>()
        row.add(1)
        for (k in 1..n) {
            row.add((row[row.size - 1] * (n - k + 1).toLong() / k).toInt())
        }
        return row
    }

    fun getRow1(rowIndex: Int): List<Int> {
        val result = ArrayList<Int>(rowIndex + 1).apply {
            repeat(rowIndex + 1) { add(0) }
        }

        repeat(rowIndex + 1) { i ->
            if (i == 0) {
                result[i] = 1
            } else {
                for (j in i downTo 0) {
                    result[j] = result[j] + if (j == 0) 0 else result[j - 1]
                }
            }
        }

        return result
    }

    fun reverseWords(s: String): String {
        return s.split(" ").reversed().filter { it.isNotEmpty() }.joinToString(" ")
    }

    fun reverseWords1(s: String): String {
        return s.split(" ").joinToString(" ") { it.reversed() }
    }
}

fun main() {
    println(ArrayString().getRow(2))
}