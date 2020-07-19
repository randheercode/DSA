package problems.lc_contest.w.w198

import generateIntArray

/**
 * Created by randheercode
 * LeetCode Contest.
 */
private class Prob1 {
    fun numWaterBottles(numBottles: Int, numExchange: Int): Int {
        var canDrink = numBottles
        var emptyBottles = numBottles

        while (emptyBottles >= numExchange) {
            val bottlesCanGet = emptyBottles / numExchange
            canDrink += bottlesCanGet
            emptyBottles = (emptyBottles.rem(numExchange)).plus(bottlesCanGet)
        }

        return canDrink
    }
}

private fun test1() {
    println(Prob1().numWaterBottles(9, 3))
    println(Prob1().numWaterBottles(15, 4))
    println(Prob1().numWaterBottles(5, 5))
    println(Prob1().numWaterBottles(2, 3))
    println(Prob1().numWaterBottles(24, 4))
}

private class Prob2 {
    // Wrong Answer
    fun countSubTrees(n: Int, edges: Array<IntArray>, labels: String): IntArray {
        val count = mutableMapOf<Int, MutableMap<Char, Int>>()
        repeat(n) {
            val map = mutableMapOf<Char, Int>()
            map[labels[it]] = 1
            count[it] = map
        }
        val adj = mutableMapOf<Int, MutableList<Int>>()
        edges.forEach {
            adj[it[0]] = adj.getOrDefault(it[0], mutableListOf())
            adj[it[0]]?.add(it[1])
        }
        for (i in n - 1 downTo 0) {
            val currentAdj = adj[i] ?: mutableListOf()
            val label = labels[i]
            val currentCount = count[i]!!
            currentAdj.forEach { ad ->
                val adjCount = count[ad]!!
                adjCount.forEach {
                    val adjKey = it.key
                    if (currentCount.containsKey(adjKey)) currentCount[adjKey] = currentCount[adjKey]!!.plus(adjCount[adjKey]!!)
                    else currentCount[adjKey] = adjCount[adjKey]!!
                }
            }
            count[i] = currentCount
        }
        val result = IntArray(n) { 1 }
        repeat(n) {
            result[it] = count[it]!![labels[it]]!!
        }
        return result
    }
}

private fun test2() {
    println(Prob2().countSubTrees(7, generateIntArray("[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]]"), "abaedcd").toList())
    println(Prob2().countSubTrees(4, generateIntArray("[[0,1],[1,2],[0,3]]"), "bbbb").toList())
    println(Prob2().countSubTrees(5, generateIntArray("[[0,1],[0,2],[1,3],[0,4]]"), "aabab").toList())
    println(Prob2().countSubTrees(6, generateIntArray("[[0,1],[0,2],[1,3],[3,4],[4,5]]"), "cbabaa").toList())
    println(Prob2().countSubTrees(7, generateIntArray("[[0,1],[1,2],[2,3],[3,4],[4,5],[5,6]]"), "aaabaaa").toList())
    println(Prob2().countSubTrees(4, generateIntArray("[[0,2],[0,3],[1,2]"), "aaabaaa").toList())
}

private class Prob3 {

}

private fun test3() {
    println(Prob3())
}

private class Prob4 {

}

private fun test4() {
    println(Prob4())
}

fun main() {
    when (2) {
        1 -> test1()
        2 -> test2()
        3 -> test3()
        4 -> test4()
    }
}
