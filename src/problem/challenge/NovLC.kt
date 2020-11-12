package problem.challenge

import problem.facebook.old.TreeNode
import java.lang.StrictMath.pow
import java.util.*


class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

class NovLC {

    fun canAttendMeetings(intervals: Array<IntArray>): Boolean {
        intervals.sortBy { it[0] }
        for (i in 1 until intervals.size) {
            if (intervals[i - 1][1] > intervals[i][0]) return false
        }
        return true
    }

    fun getDecimalValue(head: ListNode?): Int {
        var count = 0
        var current = head
        while (current?.next != null) {
            current = current.next
            count++
        }
        var result = 0
        current = head
        while (current != null) {
            if (current.`val` == 1) {
                result += pow(2.0, count.toDouble()).toInt()
            }
            current = current.next
            count--
        }
        return result
    }

    fun getDecimalValueOptimal(head: ListNode?): Int {
        var head = head
        var num = head!!.`val`
        while (head?.next != null) {
            num = num shl 1 or head.next!!.`val`
            head = head.next
        }
        return num
    }

    fun insertionSortList(head: ListNode?): ListNode? {

        val resultHead = ListNode(Int.MIN_VALUE)
        var current = head

        while (current != null) {

            // Copy and Detach
            val temp = current
            current = current.next
            temp.next = null

            var sortedCurrent: ListNode? = resultHead
            while (sortedCurrent?.next != null && sortedCurrent.next!!.`val` <= temp.`val`) {
                sortedCurrent = sortedCurrent.next
            }
            val tempNext = sortedCurrent?.next
            sortedCurrent?.next = temp
            temp.next = tempNext
        }
        return resultHead.next
    }

    fun findMinHeightTrees(n: Int, edges: Array<IntArray>): List<Int>? {

        // base cases
        if (n < 2) {
            val centroids = ArrayList<Int>()
            for (i in 0 until n) centroids.add(i)
            return centroids
        }

        // Build the graph with the adjacency list
        val neighbors = ArrayList<MutableSet<Int>>()
        for (i in 0 until n) neighbors.add(HashSet())
        for (edge in edges) {
            val start = edge[0]
            val end = edge[1]
            neighbors[start].add(end)
            neighbors[end].add(start)
        }

        // Initialize the first layer of leaves
        var leaves = ArrayList<Int>()
        for (i in 0 until n) if (neighbors[i].size == 1) leaves.add(i)

        // Trim the leaves until reaching the centroids
        var remainingNodes = n
        while (remainingNodes > 2) {
            remainingNodes -= leaves.size
            val newLeaves = ArrayList<Int>()

            // remove the current leaves along with the edges
            for (leaf in leaves) {
                for (neighbor in neighbors[leaf]) {
                    neighbors[neighbor].remove(leaf)
                    if (neighbors[neighbor].size == 1) newLeaves.add(neighbor)
                }
            }

            // prepare for the next round
            leaves = newLeaves
        }

        // The remaining nodes are the centroids of the graph
        return leaves
    }

    fun minCostToMoveChips(position: IntArray): Int {
        var evenCnt = 0
        var oddCnt = 0
        for (i in position) {
            if (i % 2 == 0) {
                evenCnt++
            } else {
                oddCnt++
            }
        }
        return minOf(oddCnt, evenCnt)
    }


    fun maxAncestorDiff(root: TreeNode?): Int {
        return if (root == null) {
            0
        } else helper(root, root.`val`, root.`val`)
    }

    private fun helper(node: TreeNode?, curMax: Int, curMin: Int): Int {
        // if encounter leaves, return the max-min along the path
        var curMax = curMax
        var curMin = curMin
        if (node == null) {
            return curMax - curMin
        }
        // else, update max and min
        // and return the max of left and right subtrees
        curMax = maxOf(curMax, node.`val`)
        curMin = minOf(curMin, node.`val`)
        val left = helper(node.left, curMax, curMin)
        val right = helper(node.right, curMax, curMin)
        return maxOf(left, right)
    }

    fun flipAndInvertImage(A: Array<IntArray>): Array<IntArray> {
        fun reverseAndFlip(arr: IntArray) {
            var s = 0
            var e = arr.lastIndex
            while (s < e) {
                val t = arr[s]
                arr[s] = arr[e].plus(1).rem(2)
                arr[e] = t.plus(1).rem(2)
                s++
                e--
            }
            if (s == e) arr[s] = arr[s].plus(1).rem(2)
        }
        for (i in A.indices) reverseAndFlip(A[i])
        return A
    }

    fun validSquare(p1: IntArray, p2: IntArray, p3: IntArray, p4: IntArray): Boolean {
        fun dist(p1: IntArray, p2: IntArray): Double {
            return ((p2[1] - p1[1]) * (p2[1] - p1[1]) + (p2[0] - p1[0]) * (p2[0] - p1[0])).toDouble()
        }

        val p = arrayOf(p1, p2, p3, p4)
        Arrays.sort(p) { l1: IntArray, l2: IntArray -> if (l2[0] == l1[0]) l1[1] - l2[1] else l1[0] - l2[0] }
        return dist(p[0], p[1]) != 0.0 && dist(p[0], p[1]) == dist(p[1], p[3]) && dist(p[1], p[3]) == dist(p[3], p[2]) && dist(p[3], p[2]) == dist(p[2], p[0]) && dist(p[0], p[3]) == dist(p[1], p[2])
    }

}
