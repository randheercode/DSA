package problem.facebook.fb_1

import java.util.*

/**
 * Created by randheercode
 * Date: 3/11/20
 * Time: 10:20 am
 */
class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

class BinaryTreeRightSideView {

    fun rightSideView(root: TreeNode?): List<Int> {
        val result = mutableListOf<Int>()
        if (root == null) return result
        val levelNode = LinkedList<TreeNode>()
        val nextLevel = LinkedList<TreeNode>()
        levelNode.offer(root)
        while (levelNode.isNotEmpty()) {
            val currentNode = levelNode.poll()
            if (levelNode.isEmpty()) result.add(currentNode.`val`)
            if (currentNode.left != null) nextLevel.add(currentNode.left!!)
            if (currentNode.right != null) nextLevel.add(currentNode.right!!)
            if (levelNode.isEmpty()) {
                levelNode.addAll(nextLevel)
                nextLevel.clear()
            }
        }
        return result
    }

}