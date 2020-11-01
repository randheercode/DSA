package problem.facebook.fb_1

import java.util.*

/**
 * Created by randheercode
 * Date: 1/11/20
 * Time: 9:16 pm
 */
class MinimumRemoveToMakeValidParentheses {
    fun minRemoveToMakeValid(s: String): String {

        val idxToRemove = hashSetOf<Int>()
        val openIdx = Stack<Int>()

        for (i in s.indices) {
            val char = s[i]
            if (char == '(') {
                openIdx.push(i)
            } else if (char == ')') {
                if (openIdx.isEmpty()) {
                    idxToRemove.add(i)
                } else {
                    openIdx.pop()
                }
            }
        }

        while (openIdx.isNotEmpty()) {
            idxToRemove.add(openIdx.pop())
        }

        val result = StringBuilder()

        for (i in s.indices) {
            if (!idxToRemove.contains(i)) result.append(s[i])
        }

        return result.toString()

    }
}