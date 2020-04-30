package problems

/**
 * Created by randheercode
 * Date: 29/4/20
 * Time: 8:25 pm
 * Statement: Binary Tree Max Path Sum Problem from LeetCode.
 */

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

class BinaryTreeMaxPathSum {
    var maxValue = 0

    fun maxPathSum(root: TreeNode?): Int {
        maxValue = Int.MIN_VALUE
        maxPathDown(root)
        return maxValue
    }

    private fun maxPathDown(node: TreeNode?): Int {
        if (node == null) return 0
        val left = Math.max(0, maxPathDown(node.left))
        val right = Math.max(0, maxPathDown(node.right))
        maxValue = Math.max(maxValue, left + right + node.`val`)
        return Math.max(left, right) + node.`val`
    }
}

fun main() {
    val root = TreeNode(1)
    root.left = TreeNode(2)
    println(BinaryTreeMaxPathSum().maxPathSum(root))
}