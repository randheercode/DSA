package problem.old

/**
 * Created by randheercode
 * Date: 4/5/20
 * Time: 4:37 pm
 * Statement: You're given strings J representing the types of stones that are jewels, and S representing the stones you have.  Each character in S is a type of stone you have.
 * You want to know how many of the stones you have are also jewels.
 */
class JewelStones {
    fun numJewelsInStones(J: String, S: String): Int {
        var jCount = 0
        for (i in S.indices) {
            if (J.contains(S[i])) {
                jCount += 1
            }
        }
        return jCount
    }
}

fun main() {
    println(JewelStones().numJewelsInStones("aA", "aAAAaaBbbBBB"))
}