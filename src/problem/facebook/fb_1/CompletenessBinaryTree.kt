package problem.facebook.fb_1

import java.util.*


/**
 * Created by randheercode
 * Date: 15/11/20
 * Time: 11:38 am
 */
class CompletenessBinaryTree {
    fun isCompleteTree(root: TreeNode?): Boolean {
        var end = false
        val queue: Queue<TreeNode?> = LinkedList()
        queue.add(root)
        while (!queue.isEmpty()) {
            val cur = queue.poll()
            if (cur == null) end = true else {
                if (end) return false
                queue.add(cur.left)
                queue.add(cur.right)
            }
        }
        return true
    }
}