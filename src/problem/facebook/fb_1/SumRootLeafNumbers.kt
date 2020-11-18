package problem.facebook.fb_1

import java.util.*


/**
 * Created by randheercode
 * Date: 18/11/20
 * Time: 11:48 am
 */
class SumRootLeafNumbers {
    fun sumNumbers(root: TreeNode?): Int {
        var root: TreeNode? = root ?: return 0
        var rootToLeaf = 0
        var currNumber = 0
        val stack: Deque<Pair<TreeNode, Int>> = LinkedList()
        stack.push(Pair(root!!, 0))
        while (!stack.isEmpty()) {
            val p = stack.pop()
            root = p.first
            currNumber = p.second
            currNumber = currNumber * 10 + root.`val`
            // if it's a leaf, update root-to-leaf sum
            if (root.left == null && root.right == null) {
                rootToLeaf += currNumber
            } else {
                if (root.right != null) stack.push(Pair(root.right!!, currNumber))
                if (root.left != null) stack.push(Pair(root.left!!, currNumber))
            }
        }
        return rootToLeaf
    }
}