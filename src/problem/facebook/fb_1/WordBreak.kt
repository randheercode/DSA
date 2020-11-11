package problem.facebook.fb_1

import java.util.*


/**
 * Created by randheercode
 * Date: 11/11/20
 * Time: 10:06 am
 */
class WordBreak {
    fun wordBreak(s: String, wordDict: List<String>): Boolean {
        val wordDictSet: Set<String> = HashSet(wordDict)
        val queue: Queue<Int> = LinkedList()
        val visited = IntArray(s.length)
        queue.add(0)
        while (!queue.isEmpty()) {
            val start = queue.remove()
            if (visited[start] == 0) {
                for (end in start + 1..s.length) {
                    if (wordDictSet.contains(s.substring(start, end))) {
                        queue.add(end)
                        if (end == s.length) {
                            return true
                        }
                    }
                }
                visited[start] = 1
            }
        }
        return false
    }
}