package problem.old

import java.util.*

/**
 * Created by randheercode
 * Date: 30/7/20
 * Time: 10:30 pm
 * https://leetcode.com/problems/word-break-ii/
 * <b>TLE</b>
 */
class WordBreak2 {
    fun wordBreakTLE(s: String, wordDict: List<String>): List<String> {
        val result = mutableListOf<String>()
        val dict = wordDict.toHashSet()
        if (s.isEmpty()) return listOf()

        fun breakWord(start: Int, end: Int, prefix: String) {
            val endReached = end == s.length
            val key = s.substring(start, end)
            if (dict.contains(key)) {
                println(key)
                val word = "$prefix $key".trim()
                if (endReached) {
                    result.add(word)
                } else {
                    breakWord(start, end + 1, prefix)
                    breakWord(end, end + 1, word)
                }
            } else {
                if (!endReached) breakWord(start, end + 1, prefix)
            }
        }

        breakWord(0, 1, "")

        return result
    }

    fun wordBreak(s: String, wordDict: List<String>): List<String> {
        val map = mutableMapOf<Int, MutableList<String>>()

        fun wordBreakHelper(input: String, end: Int, wordDict: List<String>): List<String> {
            val res = ArrayList<String>()
            if (end == 0) return listOf("")
            if (map.containsKey(end)) {
                return map[end]!!
            }
            for (start in 0 until end) {
                val sub = input.substring(start, end)
                if (wordDict.contains(sub)) {
                    val tmpList = wordBreakHelper(input, start, wordDict)
                    for (tmpStr in tmpList) {
                        res.add(if (tmpStr.isEmpty()) sub else "$tmpStr $sub")
                    }
                }
            }
            map[end] = res
            return res
        }
        return wordBreakHelper(s, s.length, wordDict)
    }

}

fun main() {
    val word = "catsanddog"
    val dict = listOf("cat", "cats", "and", "sand", "dog")
    println(WordBreak2().wordBreak(word, dict))
    val word1 = "pineapplepenapple"
    val dict1 = listOf("apple", "pen", "applepen", "pine", "pineapple")
    println(WordBreak2().wordBreak(word1, dict1))
    val word2 = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
    val dict2 = listOf("a", "aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa", "aaaaaaaa", "aaaaaaaaa", "aaaaaaaaaa")
    println(WordBreak2().wordBreak(word2, dict2))
}