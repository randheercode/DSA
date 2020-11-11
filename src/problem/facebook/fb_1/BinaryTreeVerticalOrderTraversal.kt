package problem.facebook.fb_1

import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.set


/**
 * Created by randheercode
 * Date: 11/11/20
 * Time: 9:44 am
 */
class BinaryTreeVerticalOrderTraversal {
    fun verticalOrder(root: TreeNode?): List<List<Int>> {
        var root = root
        val output: MutableList<List<Int>> = ArrayList()
        if (root == null) {
            return output
        }
        val columnTable: MutableMap<Int, ArrayList<Int>> = HashMap<Int, ArrayList<Int>>()
        val queue: Queue<Pair<TreeNode?, Int>> = LinkedList()
        var column = 0
        queue.offer(Pair(root, column))
        while (!queue.isEmpty()) {
            val p = queue.poll()
            root = p.first
            column = p.second
            if (root != null) {
                if (!columnTable.containsKey(column)) {
                    columnTable[column] = ArrayList<Int>()
                }
                columnTable[column]!!.add(root.`val`)
                queue.offer(Pair(root.left, column - 1))
                queue.offer(Pair(root.right, column + 1))
            }
        }
        val sortedKeys: List<Int> = ArrayList(columnTable.keys)
        Collections.sort(sortedKeys)
        for (k in sortedKeys) {
            output.add(columnTable[k]!!)
        }
        return output
    }
}