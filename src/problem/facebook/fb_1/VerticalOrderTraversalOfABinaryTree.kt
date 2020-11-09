package problem.facebook.fb_1

import java.util.*
import kotlin.collections.ArrayList


/**
 * Created by randheercode
 * Date: 9/11/20
 * Time: 6:28 pm
 */

class VerticalOrderTraversalOfABinaryTree {
    class Triplet<F, S, T>(val first: F, val second: S, val third: T)

    private var nodeList: MutableList<Triplet<Int, Int, Int>> = ArrayList()
    private fun bfs(root: TreeNode) {
        var root: TreeNode? = root
        val queue: Queue<Triplet<TreeNode?, Int, Int>> = LinkedList()
        var row = 0
        var column = 0
        queue.offer(Triplet(root, row, column))
        while (!queue.isEmpty()) {
            val triplet = queue.poll()
            root = triplet.first
            row = triplet.second
            column = triplet.third
            if (root != null) {
                nodeList.add(Triplet(column, row, root.`val`))
                queue.offer(Triplet(root.left, row + 1, column - 1))
                queue.offer(Triplet(root.right, row + 1, column + 1))
            }
        }
    }

    fun verticalTraversal(root: TreeNode?): List<List<Int?>?> {
        val output: MutableList<List<Int>> = ArrayList()
        if (root == null) {
            return output
        }

        // step 1). BFS traversal
        bfs(root)

        // step 2). sort the global list by <column, row, value>
        Collections.sort(nodeList, Comparator<Triplet<Int, Int, Int>> { t1, t2 -> if (t1.first == t2.first) if (t1.second == t2.second) t1.third - t2.third else t1.second - t2.second else t1.first - t2.first })

        // step 3). extract the values, partitioned by the column index.
        var currColumn: MutableList<Int> = ArrayList<Int>()
        var currColumnIndex = nodeList[0].first
        for (triplet in nodeList) {
            val column = triplet.first
            val value = triplet.third
            if (column == currColumnIndex) {
                currColumn.add(value)
            } else {
                output.add(currColumn)
                currColumnIndex = column
                currColumn = ArrayList<Int>()
                currColumn.add(value)
            }
        }
        output.add(currColumn)
        return output
    }
}