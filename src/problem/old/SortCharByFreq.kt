package problem.old

/**
 * Created by randheercode
 * Date: 22/5/20
 * Time: 6:06 pm
 * Problem Statement: Given a string, sort it in decreasing order based on the frequency of characters.
 */
class SortCharByFreq {
    fun frequencySort(s: String): String {
        return s.groupingBy { it }.eachCount().toList().sortedBy { it.second }.reversed().map { c ->
            c.first.toString().repeat(c.second)
        }.joinToString("")
    }
}


fun main() {
    println(SortCharByFreq().frequencySort("tree"))
    println(SortCharByFreq().frequencySort("cccaaa"))
    println(SortCharByFreq().frequencySort("Aabb"))
}

