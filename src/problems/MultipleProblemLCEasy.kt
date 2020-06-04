package problems

import java.util.*


/**
 * Created by randheercode
 * Date: 3/6/20
 * Time: 5:46 pm
 * Problems:
 * https://leetcode.com/problems/reverse-string-ii/
 * https://leetcode.com/problems/strobogrammatic-number/
 * https://leetcode.com/problems/string-compression/
 */
class MultipleProblemLCEasy {
    fun reverseStr(s: String, k: Int): String {
        val resultBuilder = StringBuilder()
        var startIndex = 0
        val oneGo = 2.times(k)
        val length = s.length
        while (startIndex + oneGo < length) {
            resultBuilder.append(s.substring(startIndex until startIndex.plus(k)).reversed())
            resultBuilder.append(s.substring(startIndex.plus(k) until startIndex.plus(oneGo)))
            startIndex = startIndex.plus(oneGo)
        }

        if (length.minus(startIndex) >= k) {
            resultBuilder.append(s.substring(startIndex until startIndex.plus(k)).reversed())
            resultBuilder.append(s.substring(startIndex.plus(k) until length))
        } else {
            resultBuilder.append(s.substring(startIndex until length).reversed())
        }
        return resultBuilder.toString()
    }

    fun isStrobogrammatic(num: String): Boolean {
        var start = 0
        var end: Int = num.lastIndex
        while (start <= end) {
            when (num[start]) {
                '0', '1', '8' -> if (num[end] != num[start]) {
                    return false
                }
                '6' -> if (num[end] != '9') {
                    return false
                }
                '9' -> if (num[end] != '6') {
                    return false
                }
                else -> return false
            }
            start++
            end--
        }
        return true
    }

    fun compress(chars: CharArray): Int {
        val length = chars.size
        var scanIndex = 0
        var startIndex = 0
        var writeIndex = 0
        var currentChar: Char
        while (scanIndex < length) {
            currentChar = chars[scanIndex]
            while (scanIndex + 1 < length && chars[scanIndex + 1] == currentChar) scanIndex += 1
            val charCount = scanIndex - startIndex + 1
            if (charCount == 1) {
                chars[writeIndex] = currentChar
                writeIndex += 1
                scanIndex += 1
                startIndex = scanIndex
            } else {
                chars[writeIndex] = currentChar
                writeIndex += 1
                val charCountS = charCount.toString()
                for (i in charCountS.indices) {
                    chars[writeIndex] = charCountS[i]
                    writeIndex += 1
                }
                scanIndex += 1
                startIndex = scanIndex
            }
        }
        return writeIndex
    }

    fun countAndSay(n: Int): String {
        if (n == 1) return "1"
        val last = countAndSay(n - 1)
        val length = last.length
        val builder = StringBuilder()
        var start = 0
        var current = 0
        var char: Char
        while (current < length) {
            char = last[current]
            while (current + 1 < length && last[current + 1] == char) current += 1
            val count = current - start + 1
            builder.append(count).append(char)
            start = current + 1
            current = start
        }
        return builder.toString()

    }

    fun countBinarySubString(s: String): Int {
        var ans = 0
        var prev = 0
        var cur = 1
        for (i in 1 until s.length) {
            if (s[i - 1] != s[i]) {
                ans += minOf(prev, cur)
                prev = cur
                cur = 1
            } else {
                cur++
            }
        }
        return ans + minOf(prev, cur)
    }

    fun twoSum(numbers: IntArray, target: Int): IntArray {
        val indices = IntArray(2)
        if (numbers.size < 2) return indices
        var left = 0
        var right: Int = numbers.size - 1
        while (left < right) {
            val v: Int = numbers.get(left) + numbers.get(right)
            if (v == target) {
                indices[0] = left + 1
                indices[1] = right + 1
                break
            } else if (v > target) {
                right--
            } else {
                left++
            }
        }
        return indices
    }

    fun twoSumAnother(numbers: IntArray, target: Int): IntArray {
        for (i in numbers.indices) {
            val j = numbers.binarySearch(target - numbers[i], i + 1)
            if (j > 0) return intArrayOf(i + 1, j + 1)
        }
        return intArrayOf()
    }

    fun twoSumAnother1(numbers: IntArray, target: Int): IntArray {
        var l = 0
        var r: Int = numbers.size - 1
        while (numbers[l] + numbers[r] != target) {
            if (numbers[l] + numbers[r] > target) r-- else l++
        }
        return intArrayOf(l + 1, r + 1)
    }

    fun isIsomorphic(s: String, t: String): Boolean {
        val mapS = mutableMapOf<Char, Char>()
        val mapT = mutableMapOf<Char, Char>()
        for (i in s.indices) {
            val sMapping = mapS.getOrPut(s[i]) { t[i] }
            val tMapping = mapT.getOrPut(t[i]) { s[i] }
            if (sMapping != t[i] || tMapping != s[i])
                return false
        }
        return true
    }

    fun isHappy(n: Int): Boolean {
        fun getNext(n: Int): Int {
            var n = n
            var totalSum = 0
            while (n > 0) {
                val d = n % 10
                n /= 10
                totalSum += d * d
            }
            return totalSum
        }

        var n = n
        val seen: MutableSet<Int> = HashSet()
        while (n != 1 && !seen.contains(n)) {
            seen.add(n)
            n = getNext(n)
        }
        return n == 1
    }

    fun constructRectangle(area: Int): IntArray? {
        var w = Math.sqrt(area.toDouble()).toInt()
        while (area % w != 0) w--
        return intArrayOf(area / w, w)
    }

    fun prevPermOpt1(A: IntArray): IntArray {
        val length = A.size
        var first = length.minus(2)
        while (first >= 0 && A[first] <= A[first + 1]) first -= 1
        if (first < 0) return A
        var second = length.minus(1)
        while (second > first && A[second] >= A[first]) second--
        while (A[second] == A[second - 1]) second--
        A[second] = A[first] + A[second]
        A[first] = A[second] - A[first]
        A[second] = A[second] - A[first]
        return A
    }

    fun canConstruct(ransomNote: String, magazine: String): Boolean {
        val ransomNotes = ransomNote.groupingBy { it }.eachCount()
        val magazines = magazine.groupingBy { it }.eachCount()
        return ransomNotes.none { magazines[it.key] ?: 0 < it.value }
    }

    fun isMonotonic(A: IntArray): Boolean {
        if (A.size <= 1) return true
        val range = 1..A.lastIndex
        var increasing = false
        for (i in range) {
            if (A[i - 1] > A[i]) {
                increasing = false
                break
            } else if (A[i - 1] < A[i]) {
                increasing = true
                break
            }
        }
        for (i in 1..A.lastIndex) {
            if ((A[i - 1] > A[i] && increasing) || (A[i - 1] < A[i] && !increasing)) {
                return false
            }
        }
        return true
    }

    fun isMonotonic1(A: IntArray): Boolean {
        var k = 0
        var a = 0
        for (i in 0 until A.size - 1) {
            if (A[i] >= A[i + 1])
                k++
            if (A[i] <= A[i + 1])
                a++
        }
        return k == A.size - 1 || a == A.size - 1
    }

    fun moveZeroes(nums: IntArray) {
        var cp = 0
        var wp = 0
        for (i in nums.indices) {
            if (nums[cp] != 0) {
                nums[wp] = nums[cp]
                if (wp != cp) nums[cp] = 0
                cp++
                wp++
            } else {
                cp++
            }
        }
    }

    fun reverseString(s: CharArray): Unit {
        var start = 0
        var end = s.lastIndex
        var temp: Char
        while (start < end) {
            temp = s[start]
            s[start] = s[end]
            s[end] = temp
            start++
            end--
        }
    }

}

fun main() {
//     testReverseString2()
//    testisStrobogrammatic()
//    testStringCompression()
//    testCountAndSay()
//    testBinaryStringCount()
//    test2Sum()
//    testIsIsomorphic()
    testMonotonic()
}

private fun testReverseString2() {
    println(MultipleProblemLCEasy().reverseStr("abcd", 4))
}

private fun testisStrobogrammatic() {
    println(MultipleProblemLCEasy().isStrobogrammatic("123"))
}

private fun testStringCompression() {
    println(MultipleProblemLCEasy().compress(charArrayOf('a', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b')))
}

private fun testCountAndSay() {
    println(MultipleProblemLCEasy().countAndSay(10))
}

private fun testBinaryStringCount() {
    println(MultipleProblemLCEasy().countBinarySubString("1010"))
}

private fun test2Sum() {
    println(MultipleProblemLCEasy().twoSum(intArrayOf(0, 0, 3, 4), 0))
    println(MultipleProblemLCEasy().twoSumAnother(intArrayOf(2, 7, 11, 15), 9))
    println(MultipleProblemLCEasy().twoSumAnother1(intArrayOf(2, 7, 11, 15), 9))
}

private fun testIsIsomorphic() {
    println(MultipleProblemLCEasy().isIsomorphic("egg", "add"))
    println(MultipleProblemLCEasy().isIsomorphic("foo", "bar"))
    println(MultipleProblemLCEasy().isIsomorphic("paper", "title"))
    println(MultipleProblemLCEasy().isIsomorphic("ab", "aa"))
}

private fun testMonotonic() {
    println(MultipleProblemLCEasy().isMonotonic(intArrayOf(1, 2, 2, 3)))
}
