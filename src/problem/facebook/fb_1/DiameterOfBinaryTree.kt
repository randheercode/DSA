package problem.facebook.fb_1

/**
 * Created by randheercode
 * Date: 9/11/20
 * Time: 7:15 pm
 */
class DiameterOfBinaryTree {
    var ans = 0
    fun diameterOfBinaryTree(root: TreeNode?): Int {
        ans = 1
        depth(root)
        return ans - 1
    }

    private fun depth(node: TreeNode?): Int {
        if (node == null) return 0
        val l = depth(node.left)
        val r = depth(node.right)
        ans = maxOf(ans, l + r + 1)
        return maxOf(l, r) + 1
    }
}