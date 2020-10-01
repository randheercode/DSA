package problem.facebook

// https://leetcode.com/problems/goat-latin/
class GoatLatin {
    fun toGoatLatin(S: String): String {
        val words = S.split(" ").toMutableList()
        var suffix = "a"
        val vowels = mutableMapOf(
                'a' to true,
                'e' to true,
                'i' to true,
                'o' to true,
                'u' to true,
                'A' to true,
                'E' to true,
                'I' to true,
                'O' to true,
                'U' to true
        )

        for (i in words.indices) {
            if (vowels[words[i][0]] == true) {
                words[i] = words[i] + "ma" + suffix
            } else {
                words[i] = words[i].substring(1 until words[i].length) + words[i][0] + "ma" + suffix
            }
            suffix += "a"
        }
        return words.joinToString(" ")
    }
}

fun main() {

}