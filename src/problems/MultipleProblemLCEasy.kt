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
}

fun main() {
    println(MultipleProblemLCEasy().reverseStr("abcdefg", 2))
    println(MultipleProblemLCEasy().reverseStr("abcd", 4))
}