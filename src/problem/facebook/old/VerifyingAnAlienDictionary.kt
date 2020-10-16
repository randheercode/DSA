package problem.facebook.old

// https://leetcode.com/problems/verifying-an-alien-dictionary/
class VerifyingAnAlienDictionary {
    fun isAlienSorted(words: Array<String>, order: String): Boolean {
        val charIdx = order.mapIndexed { idx, char -> char to idx }.toMap()
        search@ for (i in 1 until words.size) {
            val wordPre = words[i - 1]
            val wordPost = words[i]
            var k = -1
            for (j in 0 until minOf(wordPre.length, wordPost.length)) {
                if (wordPre[j] != wordPost[j]) {
                    if (charIdx[wordPost[j]]!! > charIdx[wordPre[j]]!!) {
                        k = j
                        continue@search
                    } else {
                        return false
                    }
                }
            }
            if (k == -1 && wordPost.length < wordPre.length) {
                return false
            }
        }
        return true
    }
}

fun main() {
    println(VerifyingAnAlienDictionary().isAlienSorted(
            arrayOf("hello", "leetcode"),
            "hlabcdefgijkmnopqrstuvwxyz"
    ))
    println(VerifyingAnAlienDictionary().isAlienSorted(
            arrayOf("word", "world", "row"),
            "worldabcefghijkmnpqstuvxyz"
    ))
    println(VerifyingAnAlienDictionary().isAlienSorted(
            arrayOf("apple", "app"),
            "abcdefghijklmnopqrstuvwxyz"
    ))
}