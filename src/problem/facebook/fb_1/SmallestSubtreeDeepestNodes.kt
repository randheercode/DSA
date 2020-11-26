package problem.facebook.fb_1

import java.util.*


/**
 * Created by randheercode
 * Date: 26/11/20
 * Time: 9:55 am
 */
class SmallestSubtreeDeepestNodes {
    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    var depth: MutableMap<TreeNode?, Int> = mutableMapOf()
    var maxDepth = 0

    fun subtreeWithAllDeepest(root: TreeNode?): TreeNode? {
        depth[null] = -1
        dfs(root, null)
        maxDepth = -1
        for (d in depth.values) maxDepth = maxOf(maxDepth, d)
        return answer(root)
    }

    fun dfs(node: TreeNode?, parent: TreeNode?) {
        if (node != null) {
            depth[node] = depth[parent]!! + 1
            dfs(node.left, node)
            dfs(node.right, node)
        }
    }

    fun answer(node: TreeNode?): TreeNode? {
        if (node == null || depth!![node] == maxDepth) return node
        val L = answer(node.left)
        val R = answer(node.right)
        return if (L != null && R != null) node else L ?: R
    }
    
}