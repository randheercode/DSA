package problems

import utils.TreeNode
import java.util.*


/**
 * Created by randheercode
 * Date: 7/8/20
 * Time: 6:56 pm
 */
class VerticalOrderTraversalBinaryTreeRecursiveDFS {
    private inner class Range {
        var min = Int.MAX_VALUE
        var max = Int.MIN_VALUE
    }

    fun verticalTraversal(root: TreeNode?): List<List<Int>> {
        val map: MutableMap<Int, TreeMap<Int, PriorityQueue<Int>>> = HashMap()
        val range = Range()
        depthFirstSearch(root, 0, 0, map, range)
        return convertToList(map, range)
    }

    private fun depthFirstSearch(node: TreeNode?, x: Int, y: Int, map: MutableMap<Int, TreeMap<Int, PriorityQueue<Int>>>, range: Range) {
        if (node == null) return
        map.putIfAbsent(x, TreeMap(java.util.Comparator { y1: Int?, y2: Int -> y2.compareTo(y1!!) }))
        map[x]!!.putIfAbsent(y, PriorityQueue(java.util.Comparator { v1: Int, v2: Int? -> v1.compareTo(v2!!) }))
        map[x]!![y]!!.add(node.`val`)
        range.min = minOf(range.min, x)
        range.max = maxOf(range.max, x)
        depthFirstSearch(node.left, x - 1, y - 1, map, range)
        depthFirstSearch(node.right, x + 1, y - 1, map, range)
    }

    private fun convertToList(map: Map<Int, TreeMap<Int, PriorityQueue<Int>>>, range: Range): List<List<Int>> {
        val verticalOrders: MutableList<List<Int>> = ArrayList()
        for (x in range.min..range.max) {
            val nodeValues: MutableList<Int> = ArrayList()
            for (y in map[x]!!.keys) {
                val minHeap = map[x]!![y]
                while (!minHeap!!.isEmpty()) {
                    nodeValues.add(minHeap.poll())
                }
            }
            verticalOrders.add(nodeValues)
        }
        return verticalOrders
    }
}