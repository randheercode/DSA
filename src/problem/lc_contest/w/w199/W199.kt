package problem.lc_contest.w.w199

/**
 * Created by randheercode
 * LeetCode Contest.
 */
private class Prob1 {
    fun restoreString(s: String, indices: IntArray): String {
        val result = StringBuilder(s)
        for (i in 0..indices.lastIndex) {
            result[indices[i]] = s[i]
        }
        return result.toString()
    }
}

private fun test1() {
    println(Prob1().restoreString("codeleet", intArrayOf(4, 5, 6, 7, 0, 2, 1, 3)))
    println(Prob1().restoreString("aiohn", intArrayOf(3, 1, 4, 2, 0)))
    println(Prob1().restoreString("aaiougrt", intArrayOf(4, 0, 2, 6, 7, 3, 1, 5)))
    println(Prob1().restoreString("art", intArrayOf(1, 0, 2)))
}

private class Prob2 {
    fun minFlips(target: String): Int {
        var flipCount = 0
        var lastStage = '0'
        for (s in target) {
            if (lastStage != s) flipCount++
            lastStage = s
        }
        return flipCount
    }
}

private fun test2() {
    println(Prob2().minFlips("10111"))
    println(Prob2().minFlips("101"))
    println(Prob2().minFlips("00000"))
    println(Prob2().minFlips("001011101"))
}

private class Prob3 {

}

private fun test3() {
    println(Prob3())
}

private class Prob4 {
    // TODO Not Completed
    fun getLengthOfOptimalCompression(s: String, k: Int): Int {
        var k = k
        val count = mutableListOf<Int>()
        val character = mutableListOf<Char>()

        var lastChar = ' '
        var ind = -1
        for (i in s.indices) {
            if (lastChar == s[i]) {
                count[ind] = ++count[ind]
            } else {
                character.add(s[i])
                count.add(1)
                lastChar = s[i]
                ind++
            }
        }
        if (k > 0) {
            if (character.size == 1) {
                count[0] = count[0].minus(k)
                if (count[0] == 0) {
                    count.removeAt(0)
                    character.removeAt(0)
                }
            } else if (character.size == 2) {
                val index = if (minOf(count[0], count[1]) <= k) {
                    if (count[0] < count[1]) 0 else 1
                } else {
                    if (count[0] < count[1]) 1 else 0
                }
                count[index] = count[index].minus(k)
                if (count[index] == index) {
                    count.removeAt(index)
                    character.removeAt(index)
                }
            } else {
                val range = 1..character.lastIndex.minus(1)
                for (i in range) {
                }
            }
        }

        println(character.toString())
        println(count.toString())

        val builder = StringBuilder()
        for (i in character.indices) {
            val c = if (count[i] > 1) count[i].toString() else ""
            builder.append(character[i]).append(c)
        }
        return builder.toString().length
    }
}

private fun test4() {
    println(Prob4().getLengthOfOptimalCompression("aaaaa", 0))
}

fun main() {
    when (4) {
        1 -> test1()
        2 -> test2()
        3 -> test3()
        4 -> test4()
    }
}
