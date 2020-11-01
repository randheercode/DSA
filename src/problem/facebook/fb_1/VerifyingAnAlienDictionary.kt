package problem.facebook.fb_1

/**
 * Created by randheercode
 * Date: 30/10/20
 * Time: 10:42 am
 */
class VerifyingAnAlienDictionary {

    private val orderMap = mutableMapOf<Char, Int>()

    private fun prepareOrder(order: String) {
        for (i in order.indices) {
            orderMap[order[i]] = i
        }
    }

    private fun indexOf(char: Char): Int {
        return orderMap[char]!!
    }

    fun isAlienSorted(words: Array<String>, order: String): Boolean {
        prepareOrder(order)
        for (idx in 1 until words.size) {
            val prev = words[idx - 1]
            val curr = words[idx]
            var diffStart = -1
            for (i in 0 until minOf(prev.length, curr.length)) {
                if (indexOf(curr[i]) != indexOf(prev[i])) {
                    if (indexOf(curr[i]) > indexOf(prev[i])) {
                        diffStart = i
                        break
                    } else {
                        return false
                    }
                }
            }
            if (diffStart == -1 && prev.length > curr.length) return false
        }
        return true
    }
}