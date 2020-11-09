package problem.facebook.fb_1

/**
 * Created by randheercode
 * Date: 9/11/20
 * Time: 10:09 pm
 */
class ValidNumber {
    fun isNumber(str: String?): Boolean {
        var s = str ?: return false
        s = s.trim { it <= ' ' }
        val n = s.length
        if (n == 0) return false

        // flags
        var signCount = 0
        var hasE = false
        var hasNum = false
        var hasPoint = false
        for (i in 0 until n) {
            val c = s[i]

            // invalid character
            if (!isValid(c)) return false

            // digit is always fine
            if (c in '0'..'9') hasNum = true

            // e or E
            if (c == 'e' || c == 'E') {
                // e cannot appear twice and digits must be in front of it
                if (hasE || !hasNum) return false
                // e cannot be the last one
                if (i == n - 1) return false
                hasE = true
            }

            // decimal place
            if (c == '.') {
                // . cannot appear twice and it cannot appear after e
                if (hasPoint || hasE) return false
                // if . is the last one, digits must be in front of it, e.g. "7."
                if (i == n - 1 && !hasNum) return false
                hasPoint = true
            }

            // signs
            if (c == '+' || c == '-') {
                // no more than 2 signs
                if (signCount == 2) return false
                // sign cannot be the last one
                if (i == n - 1) return false
                // sign can appear in the middle only when e appears in front
                if (i > 0 && !hasE) return false
                signCount++
            }
        }
        return true
    }

    fun isValid(c: Char): Boolean {
        return c == '.' || c == '+' || c == '-' || c == 'e' || c == 'E' || c in '0'..'9'
    }
}