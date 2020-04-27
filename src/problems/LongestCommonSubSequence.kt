package problems

/**
 * Created by randheer.kumar
 * Date: 27/4/20
 * Time: 8:33 pm
 * Problem Statement: Given two strings text1 and text2, return the length of their longest common subsequence.
 * A subsequence of a string is a new string generated from the original string with some characters(can be none)
 * deleted without changing the relative order of the remaining characters. (eg, "ace" is a subsequence of "abcde" while "aec" is not).
 * A common subsequence of two strings is a subsequence that is common to both strings.
 * If there is no common subsequence, return 0.
 */
class LongestCommonSubSequence {
    fun longestCommonSubsequenceOther(text1: String, text2: String): Int {
        return calculateLCS(text1.toCharArray(), text2.toCharArray(), text1.lastIndex, text2.lastIndex)
    }

    private fun calculateLCS(text1: CharArray, text2: CharArray, text1Index: Int, text2Index: Int): Int {
        if (text1Index < 0 || text2Index < 0) return 0

        return if (text1[text1Index] == text2[text2Index]) {
            1 + calculateLCS(text1, text2, text1Index - 1, text2Index - 1)
        } else {
            Math.max(calculateLCS(text1, text2, text1Index - 1, text2Index),
                    calculateLCS(text1, text2, text1Index, text2Index - 1))
        }
    }

    fun longestCommonSubsequence(text1: String, text2: String): Int {
        return calculateLCS(text1, text2, text1.length, text2.length, mutableMapOf())
    }

    private fun calculateLCS(X: String, Y: String, m: Int, n: Int,
                             lookup: MutableMap<String, Int>): Int {
        if (m == 0 || n == 0) return 0

        val key = "$m|$n"

        if (!lookup.containsKey(key)) {
            if (X[m - 1] == Y[n - 1]) {
                lookup[key] = calculateLCS(X, Y, m - 1, n - 1, lookup) + 1
            } else {
                lookup[key] = Integer.max(calculateLCS(X, Y, m, n - 1, lookup), calculateLCS(X, Y, m - 1, n, lookup))
            }
        }

        return lookup[key]!!
    }
}

fun main() {
    println(LongestCommonSubSequence().longestCommonSubsequenceOther("abcde", "abc"))
    println(LongestCommonSubSequence().longestCommonSubsequenceOther("abcde", "ace"))
    println(LongestCommonSubSequence().longestCommonSubsequenceOther("abcde", "b"))
    println(LongestCommonSubSequence().longestCommonSubsequenceOther("abcde", "xyx"))

    println(LongestCommonSubSequence().longestCommonSubsequence("abcde", "abc"))
    println(LongestCommonSubSequence().longestCommonSubsequence("abcde", "ace"))
    println(LongestCommonSubSequence().longestCommonSubsequence("abcde", "b"))
    println(LongestCommonSubSequence().longestCommonSubsequence("abcde", "xyx"))
}