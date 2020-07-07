package problems

/**
 * Created by randheercode
 * Date: 7/7/20
 * Time: 2:37 pm
 */
class LongestPalindrome {
    fun longestPalindrome(s: String): String? {
        var longestPalindrome = ""
        for (index in s.indices) {
            val oddPalindrome = checkPalindrome(s, index, index)
            val evenPalindrome = checkPalindrome(s, index, index + 1)
            val palindrome = if (oddPalindrome.length > evenPalindrome.length) oddPalindrome else evenPalindrome
            longestPalindrome = if (palindrome.length > longestPalindrome.length) palindrome else longestPalindrome
        }
        return longestPalindrome
    }

    private fun checkPalindrome(str: String, start: Int, end: Int): String {
        var s = start
        var e = end
        while (s >= 0 && e < str.length && str[s] == str[e]) {
            s--
            e++
        }
        return str.substring(s + 1, e)
    }
}

fun main() {
    println(LongestPalindrome().longestPalindrome("babad"))
}