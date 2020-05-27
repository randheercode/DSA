package problems

import generateIntArray

/**
 * Created by randheercode
 * Date: 27/5/20
 * Time: 7:30 pm
 * Problem Statement: Given a set of N people (numbered 1, 2, ..., N), we would like to split everyone into two groups of any size.
 * Each person may dislike some other people, and they should not go into the same group.
 */
class PossibleBipartisan {
    fun possibleBipartition(N: Int, dislikes: Array<IntArray>): Boolean {
        fun dfs(current: Node, color: Boolean) {
            if (current.firstGroup != null) return
            current.firstGroup = color
            current.neighbors.forEach { dfs(it, !color) }
        }

        val graph = (1..N).map { it to Node(it) }.toMap()
        val edges = dislikes.map { (graph[it[0]] ?: error("")) to (graph[it[1]] ?: error("")) }.toSet().also { edges ->
            edges.forEach {
                it.first.neighbors.add(it.second)
                it.second.neighbors.add(it.first)
            }
        }
        graph.values.forEach { dfs(it, true) }
        return edges.none { it.first.firstGroup != null && it.second.firstGroup != null && it.first.firstGroup == it.second.firstGroup }
    }
}

data class Node(val value: Int, var firstGroup: Boolean? = null, val neighbors: MutableList<Node> = mutableListOf())


fun main() {
    println(PossibleBipartisan().possibleBipartition(4, generateIntArray("[[1,2],[1,3],[2,4]]")))
    println(PossibleBipartisan().possibleBipartition(3, generateIntArray("[[1,2],[1,3],[2,3]]")))
    println(PossibleBipartisan().possibleBipartition(5, generateIntArray("[[1,2],[2,3],[3,4],[4,5],[1,5]]")))
}