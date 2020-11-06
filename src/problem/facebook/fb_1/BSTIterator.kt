package problem.facebook.fb_1

import java.util.*

/**
 * Created by randheercode
 * Date: 6/11/20
 * Time: 2:59 pm
 */
class BSTIterator(root: TreeNode?) {

    private val stack = Stack<TreeNode>()

    init {
        iteratorHelper(root)
    }

    private fun iteratorHelper(root: TreeNode?) {
        var node = root
        while (node != null) {
            stack.push(node)
            node = node.left
        }
    }

    fun next(): Int {
        val node = stack.pop()
        if (node.right != node) {
            iteratorHelper(node.right)
        }
        return node.`val`
    }


    fun hasNext(): Boolean {
        return stack.isNotEmpty()
    }

}