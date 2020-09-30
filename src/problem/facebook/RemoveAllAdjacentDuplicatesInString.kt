package problem.facebook

// https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/
class RemoveAllAdjacentDuplicatesInString {
    fun removeDuplicates(S: String): String {
        val result = StringBuilder()
        for (i in S.indices) {
            if (result.isNotEmpty() && result.last() == S[i]) {
                result.deleteCharAt(result.length - 1)
            } else {
                result.append(S[i])
            }
        }
        return result.toString()
    }
}

fun main() {
    println(RemoveAllAdjacentDuplicatesInString().removeDuplicates("abbaca"))
}