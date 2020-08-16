package problem.lc_contest.w.w198

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
    lateinit var res: IntArray
    val edgesMap = HashMap<Int, MutableList<Int>>()
    lateinit var labelMap: CharArray
    lateinit var seen: HashSet<Int>

    fun countSubTrees(n: Int, edges: Array<IntArray>, labels: String): IntArray {
        res = IntArray(n)
        seen = HashSet(n)
        labelMap = CharArray(n)

        // create edge map
        edges.forEach {
            edgesMap.putIfAbsent(it[0], mutableListOf())
            edgesMap.putIfAbsent(it[1], mutableListOf())

            // Add edges in both direction. But we use a HashSet to make sure we only
            // traverse from root to children
            edgesMap[it[0]]?.add(it[1])
            edgesMap[it[1]]?.add(it[0])

        }

        // create label map
        labels.forEachIndexed { i, char ->
            labelMap[i] = char
        }
        recurse(0)
        return res
    }

    // Recursively traverse each node in the tree.
    // Each node receives an Array of count of all the labels from each of it's subtree.
    fun recurse(node: Int): IntArray {
        var labelsCount = IntArray(26)

        // Use seen set to ensure we traverse only from root towards children
        seen.add(node)
        for (child in edgesMap.getOrDefault(node, mutableListOf<Int>())) {
            if(child in seen) continue

            // get labels from it's children
            val labels = recurse(child)

            // update the count of labels so that the parent can use the information
            for (i in 0..25) {
                labelsCount[i] += labels[i]
            }
        }
        // include current node's label as well
        labelsCount[labelMap[node] - 'a']++

        // update the result for this node's label
        res[node] = labelsCount[labelMap[node] - 'a']
        return labelsCount

    }
}

private fun test2() {
    println(Prob2().countSubTrees(7, generateIntArray("[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]]"), "abaedcd").toList())
    println(Prob2().countSubTrees(4, generateIntArray("[[0,1],[1,2],[0,3]]"), "bbbb").toList())
    println(Prob2().countSubTrees(5, generateIntArray("[[0,1],[0,2],[1,3],[0,4]]"), "aabab").toList())
    println(Prob2().countSubTrees(6, generateIntArray("[[0,1],[0,2],[1,3],[3,4],[4,5]]"), "cbabaa").toList())
    println(Prob2().countSubTrees(7, generateIntArray("[[0,1],[1,2],[2,3],[3,4],[4,5],[5,6]]"), "aaabaaa").toList())
    println(Prob2().countSubTrees(4, generateIntArray("[[0,2],[0,3],[1,2]"), "aeed").toList())
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
