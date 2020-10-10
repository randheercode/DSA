package problem.facebook

import java.util.*

// https://leetcode.com/problems/longest-common-prefix/
class LongestCommonPrefix {
    fun longestCommonPrefix(strs: Array<String>): String {
        if (strs.isEmpty()) return ""
        Arrays.sort(strs, compareBy { it.length })
        if (strs[0].isEmpty()) return ""
        val result = StringBuilder()
        outer@ for (i in strs[0].indices) {
            for (j in 1..strs.lastIndex) {
                if (strs[j - 1][i] != strs[j][i]) {
                    break@outer
                }
            }
            result.append(strs[0][i])
        }
        return result.toString()
    }
}

fun main() {
    println(LongestCommonPrefix().longestCommonPrefix(arrayOf("flower", "flow", "flight")))
    println(LongestCommonPrefix().longestCommonPrefix(arrayOf("dog","racecar","car")))
}