package problems

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
}

fun main() {
//     testReverseString2()
//    testisStrobogrammatic()
//    testStringCompression()
//    testCountAndSay()
    testBinaryStringCount()
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
