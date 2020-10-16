package problem.facebook.old

import java.util.*


// https://leetcode.com/problems/alien-dictionary/
class AlienDictionary {
    fun alienOrder(words: Array<String>): String? {

        // Step 0: Create data structures and find all unique letters.
        val adjList: MutableMap<Char, MutableList<Char>> = HashMap()
        val counts: MutableMap<Char, Int> = HashMap()
        for (word in words) {
            for (c in word.toCharArray()) {
                counts[c] = 0
                adjList[c] = ArrayList()
            }
        }

        // Step 1: Find all edges.
        for (i in 0 until words.size - 1) {
            val word1 = words[i]
            val word2 = words[i + 1]
            // Check that word2 is not a prefix of word1.
            if (word1.length > word2.length && word1.startsWith(word2)) {
                return ""
            }
            // Find the first non match and insert the corresponding relation.
            for (j in 0 until minOf(word1.length, word2.length)) {
                if (word1[j] != word2[j]) {
                    adjList[word1[j]]!!.add(word2[j])
                    counts[word2[j]] = counts[word2[j]]!! + 1
                    break
                }
            }
        }

        // Step 2: Breadth-first search.
        val sb = StringBuilder()
        val queue: Queue<Char> = LinkedList()
        for (c in counts.keys) {
            if (counts[c] == 0) {
                queue.add(c)
            }
        }
        while (!queue.isEmpty()) {
            val c = queue.remove()
            sb.append(c)
            for (next in adjList[c]!!) {
                counts[next] = counts[next]!! - 1
                if (counts[next] == 0) {
                    queue.add(next)
                }
            }
        }
        return if (sb.length < counts.size) {
            ""
        } else sb.toString()
    }
}

fun main() {
    println(AlienDictionary().alienOrder(arrayOf(
            "wrt",
            "wrf",
            "er",
            "ett",
            "rftt"
    )))
}