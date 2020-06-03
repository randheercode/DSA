package problems

/**
 * Created by randheercode
 * Date: 3/6/20
 * Time: 5:46 pm
 * Problems:
 * https://leetcode.com/problems/reverse-string-ii/
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
}

fun main() {
    // testReverseString2()
    testisStrobogrammatic()
}

private fun testReverseString2() {
    println(MultipleProblemLCEasy().reverseStr("abcdefg", 2))
    println(MultipleProblemLCEasy().reverseStr("abcd", 4))
}

private fun testisStrobogrammatic() {
    println(MultipleProblemLCEasy().isStrobogrammatic("88"))
    println(MultipleProblemLCEasy().isStrobogrammatic("69"))
    println(MultipleProblemLCEasy().isStrobogrammatic("00"))
    println(MultipleProblemLCEasy().isStrobogrammatic("123"))
}

