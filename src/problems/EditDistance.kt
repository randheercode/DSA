package problems

/**
 * Created by randheercode
 * Date: 31/5/20
 * Time: 3:19 pm
 * Problem Statement:Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.
 * You have the following 3 operations permitted on a word:
 * Insert a character
 * Delete a character
 * Replace a character
 */
class EditDistance {
    fun minDistance(word1: String, word2: String): Int {
        val len1 = word1.length
        val len2 = word2.length

        var cost = IntArray(len1 + 1) { it }
        var newCost = IntArray(len1 + 1) { 0 }

        for (i in 1..len2) {
            newCost[0] = i

            for (j in 1..len1) {
                val editCost = if (word1[j - 1] == word2[i - 1]) 0 else 1

                val costReplace = cost[j - 1] + editCost
                val costInsert = cost[j] + 1
                val costDelete = newCost[j - 1] + 1

                newCost[j] = minOf(costInsert, costDelete, costReplace)
            }

            val swap = cost
            cost = newCost
            newCost = swap
        }

        return cost[len1]

    }

    fun minDistanceDP(word1: String, word2: String): Int {
        val word1Len = word1.length
        val word2Len = word2.length
        val dp = Array(word1Len + 1) { IntArray(word2Len + 1) }

        for (i in 0..word1Len) {
            for (j in 0..word2Len) {
                when {
                    i == 0 -> dp[i][j] = j
                    j == 0 -> dp[i][j] = i
                    word1[i - 1] == word2[j - 1] -> dp[i][j] = dp[i - 1][j - 1]
                    else -> dp[i][j] = 1 + minOf(dp[i][j - 1], dp[i - 1][j], dp[i - 1][j - 1])
                }
            }
        }

        return dp[word1Len][word2Len]
    }
}

fun main() {
    println(EditDistance().minDistance("horse", "ros"))
    println(EditDistance().minDistance("intention", "execution"))
    println(EditDistance().minDistanceDP("horse", "ros"))
}