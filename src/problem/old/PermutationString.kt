package problem.old

/**
 * Created by randheercode
 * Date: 17/5/20
 * Time: 4:03 pm
 * Problem Statement: Given a string s and a non-empty string p, find all the start indices of p's anagrams in string.
 * Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.
 * The order of output does not matter.
 */
class PermutationString {
    fun checkInclusion(s1: String, s2: String): Boolean {
        if (s2.length < s1.length) return false
        val countS1 = s1.groupingBy { it }.eachCount()
        val countS2 = s2.substring(0, s1.length).groupingBy { it }.eachCount().toMutableMap()
        if (countS1 == countS2) return true
        var result = false
        for (i in 1 .. s2.length.minus(s1.length)) {
            if (i != 0) {
                val count = countS2[s2[i - 1]]?.let { it - 1 } ?: 0
                if (count == 0) countS2.remove(s2[i - 1])
                else countS2[s2[i - 1]] = count
                countS2[s2[i + s1.lastIndex]] = countS2.getOrDefault(s2[i + s1.lastIndex], 0) + 1
            }
            if (countS1 == countS2) {
                result = true
                break
            }
        }
        return result
    }
}

fun main() {
    println(PermutationString().checkInclusion("ab", "eidbaooo"))
    println(PermutationString().checkInclusion("ab", "eidboaoo"))
    println(PermutationString().checkInclusion("adc", "dcda"))
}
