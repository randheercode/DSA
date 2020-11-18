package problem.facebook.fb_1

/**
 * Created by randheercode
 * Date: 18/11/20
 * Time: 11:24 am
 */
class RegularExpressionMatching {
    fun isMatch(text: String, pattern: String): Boolean {
        if (pattern.isEmpty()) return text.isEmpty()
        val firstMatch = text.isNotEmpty() && (pattern[0] == text[0] || pattern[0] == '.')
        return if (pattern.length >= 2 && pattern[1] == '*') {
            isMatch(text, pattern.substring(2)) ||
                    firstMatch && isMatch(text.substring(1), pattern)
        } else {
            firstMatch && isMatch(text.substring(1), pattern.substring(1))
        }
    }
}