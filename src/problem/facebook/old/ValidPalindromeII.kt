package problem.facebook.old

// https://leetcode.com/problems/valid-palindrome-ii/
class ValidPalindromeII {
    private fun isPalindrome(s: String, i: Int, j: Int): Boolean {
        var start = i
        var end = j
        while (start < end) {
            if (s[start] != s[end]) return false
            start++
            end--
        }
        return true
    }

    fun validPalindrome(s: String): Boolean {
        var start = 0
        var end = s.lastIndex
        while (start < end) {
            if (s[start] != s[end]) {
                return isPalindrome(s, start + 1, end) || isPalindrome(s, start, end - 1)
            }
            start++
            end--
        }
        return true
    }
}

fun main() {
    println(ValidPalindromeII().validPalindrome("lcul"))
    println(ValidPalindromeII().validPalindrome("abcaacdba"))
    println(ValidPalindromeII().validPalindrome("aguokepatgbnvfqmgmlcupuufxoohdfpgjdmysgvhmvffcnqxjjxqncffvmhvgsymdjgpfdhooxfuupuculmgmqfvnbgtapekouga"))
}