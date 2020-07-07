package problems

/**
 * Created by randheercode
 * Date: 7/7/20
 * Time: 2:37 pm
 */
class LongestPalindrome {
    fun longestPalindrome(s: String): String {
        if (s.isEmpty()) return ""
        var centerIndex = Int.MIN_VALUE
        var length = Int.MIN_VALUE
        for (i in s.indices) {
            val currentLength = palindromeLength(s, i)
            if (currentLength > length) {
                centerIndex = i
                length = currentLength
            }
        }
        println("$centerIndex $length")
        return s.substring(centerIndex.minus(length / 2), centerIndex.plus(length / 2))
    }

    private fun palindromeLength(s: String, i: Int): Int {
        var left = i - 1
        var right = i + 1
        var count = 1
        while (left >= 0 && right < s.length) {
            if (s[left] == s[right]) {
                count += 2
                left--
                right++
            } else {
                return count
            }
        }
        return count
    }
}

fun main() {
    println(LongestPalindrome().longestPalindrome("babbad"))
}