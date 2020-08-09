package problem.old


/**
 * Created by randheercode
 * Date: 28/4/20
 * Time: 9:42 pm
 * Statement:  Given a string, find the first non-repeating character
 * in it and return it's index. If it doesn't exist, return -1.
 *
 */
class FirstUniqueChar() {

    private val uniqueChar = mutableMapOf<Char, Char>()
    private val duplicateChar = mutableMapOf<Char, Char>()

    fun firstUniqChar(s: String): Int {
        s.intern().forEach { add(it) }
        return uniqueChar.values.firstOrNull()?.let { s.indexOf(it) } ?: -1
    }

    private fun add(value: Char) {
        if (duplicateChar.contains(value)) return
        if (uniqueChar.contains(value)) {
            uniqueChar.remove(value)
            duplicateChar[value] = value
        } else {
            uniqueChar[value] = value
        }
    }

}

fun main() {
    println(FirstUniqueChar().firstUniqChar("leetcode"))
    println(FirstUniqueChar().firstUniqChar("loveleetcode"))
}