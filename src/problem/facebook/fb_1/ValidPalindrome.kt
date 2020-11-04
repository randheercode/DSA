package problem.facebook.fb_1

/**
 * Created by randheercode
 * Date: 4/11/20
 * Time: 5:00 pm
 */
class ValidPalindrome {
    private fun validChar(c: Char): Boolean {
        return c.isDigit() || c.isLetter()
    }

    fun isPalindrome(s: String): Boolean {
        var start = 0
        var end = s.lastIndex
        while (start < end) {
            while (start < end && !validChar(s[start])) start++
            while (end > start && !validChar(s[end])) end--
            if (start < end) {
                if (!s[start].equals(s[end], true)) return false
                start++
                end--
            }
        }
        return true
    }
}