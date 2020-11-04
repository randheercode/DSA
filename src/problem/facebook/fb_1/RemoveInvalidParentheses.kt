package problem.facebook.fb_1

import java.util.*


/**
 * Created by randheercode
 * Date: 4/11/20
 * Time: 4:16 pm
 */
class RemoveInvalidParentheses {

    fun removeInvalidParentheses(s: String): List<String>? {
        var left = 0
        var right = 0
        var count = 0
        for (i in s.indices) {
            if (s[i] == '(') {
                left++
            } else if (s[i] == ')') {
                if (left > 0) left-- else right++
            }
        }
        // Find how many min. no. of changes are required
        count = left + right
        val res: MutableSet<String> = HashSet()
        dfs(s, 0, res, StringBuilder(""), count)
        return ArrayList(res)
    }

    fun isValid(s: String): Boolean {
        var count = 0
        for (c in s) {
            if (c == '(') count++
            if (c == ')' && count-- == 0) return false
        }
        return count == 0
    }

    fun dfs(s: String, i: Int, res: MutableSet<String>, sb: StringBuilder, count: Int) {
        if (count < 0) {
            return
        }
        if (i == s.length) {
            if (count == 0) {
                if (isValid(sb.toString())) {
                    res.add(sb.toString())
                }
            }
            return
        }
        val c = s[i]
        val len = sb.length
        if (c == '(' || c == ')') {
            dfs(s, i + 1, res, sb, count - 1) // not use ')''('
            dfs(s, i + 1, res, sb.append(c), count) // use
        } else {
            dfs(s, i + 1, res, sb.append(c), count)
        }
        sb.setLength(len)
    }
}