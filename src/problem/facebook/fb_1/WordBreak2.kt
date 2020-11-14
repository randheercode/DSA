package problem.facebook.fb_1

import java.util.*

/**
 * Created by randheercode
 * Date: 14/11/20
 * Time: 12:36 pm
 */
class WordBreak2 {
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