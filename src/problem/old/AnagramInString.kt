package problem.old

import kotlin.system.measureTimeMillis

/**
 * Created by randheercode
 * Date: 17/5/20
 * Time: 4:03 pm
 * Problem Statement: Given a string s and a non-empty string p, find all the start indices of p's anagrams in string.
 * Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.
 * The order of output does not matter.
 */
class AnagramInString {
    fun findAnagrams(S: String, P: String): List<Int> {
        val indexes = mutableListOf<Int>()
        val value = listOf(2, 3, 5, 11, 19, 23, 37, 47, 59, 79, 97, 113, 137, 163, 191, 223, 257, 293, 331, 353, 383, 431, 487, 541, 587, 631)
        val aInt = 'a'.toInt()
        val pValue = P.map { value[it.toInt() - aInt] }.reduce { acc, c -> c + acc }
        val lengthOfP = P.lastIndex
        if (S.length < P.length) return listOf()
        var cValue = S.substring(0..P.lastIndex).map { value[it.toInt() - aInt] }.reduce { acc, c -> c + acc }
        for (i in 0..S.lastIndex.minus(lengthOfP)) {
            if (i != 0) cValue = cValue.minus(value[S[i - 1].toInt() - aInt]).plus(value[S[i.plus(lengthOfP)].toInt() - aInt])
            if (cValue == pValue) {
                indexes.add(i)
            }
        }
        return indexes
    }
}

fun main() {
    val result1 = measureTimeMillis {
        println(AnagramInString().findAnagrams("cbaebabacd", "abc"))
    }
    println(result1)
    val result2 = measureTimeMillis {
        println(AnagramInString().findAnagrams("abab", "ab"))
    }
    println(result2)
    println(AnagramInString().findAnagrams("op", "by"))
}
