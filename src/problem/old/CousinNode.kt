package problem.old

import utils.TreeNode

/**
 * Created by randheercode
 * Date: 7/5/20
 * Time: 9:37 pm
 */
class CousinNode {

    fun isCousins(root: TreeNode?, x: Int, y: Int): Boolean {
        val xResult = getLevelAndParent(root, null, x, 0)
        val yResult = getLevelAndParent(root, null, y, 0)
        return xResult?.second == yResult?.second && xResult?.first != yResult?.first
    }

    private fun getLevelAndParent(root: TreeNode?, parent: TreeNode?, value: Int, level: Int): Pair<TreeNode?, Int>? {
        if (root == null) return null
        if (root.`val` == value) return Pair(parent, level)
        val rightPair = getLevelAndParent(root.right, root, value, level + 1)
        return getLevelAndParent(root.left, root, value, level + 1) ?: rightPair
    }
}
