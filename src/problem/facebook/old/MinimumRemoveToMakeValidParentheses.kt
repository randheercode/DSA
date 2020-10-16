package problem.facebook.old

import java.util.*

// https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/
class MinimumRemoveToMakeValidParentheses {
    fun minRemoveToMakeValid(s: String): String {
        val result = StringBuilder()
        val idx = mutableSetOf<Int>()
        val stack = Stack<Int>()
        for (i in s.indices) {
            if (s[i] == '(') {
                stack.push(i)
            } else {
                if (s[i] == ')') {
                    if (stack.isNotEmpty()) {
                        stack.pop()
                    } else {
                        idx.add(i)
                    }
                }
            }
        }
        while (stack.isNotEmpty()) idx.add(stack.pop())
        for (i in s.indices) {
            if (!idx.contains(i)) result.append(s[i])
        }
        return result.toString()
    }
}

fun main() {
    println(MinimumRemoveToMakeValidParentheses().minRemoveToMakeValid("lee(t(c)o)de)"))
}