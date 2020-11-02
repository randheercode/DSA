package problem.facebook.fb_1

/**
 * Created by randheercode
 * Date: 2/11/20
 * Time: 8:02 pm
 */
class ValidPalindromeII {
    private fun isPalindrome(str: String, start: Int, end: Int): Boolean {
        var left = start
        var right = end
        while (left < right) {
            if (str[left] != str[right]) return false
            left++
            right--
        }
        return true
    }

    fun validPalindrome(s: String): Boolean {
        var left = 0
        var right = s.lastIndex
        while (left < right) {
            if (s[left] != s[right]) {
                return isPalindrome(s, left + 1, right) || isPalindrome(s, left, right - 1)
            }
            left++
            right--
        }
        return true
    }
}