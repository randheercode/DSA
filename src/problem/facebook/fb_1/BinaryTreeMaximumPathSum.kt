package problem.facebook.fb_1

/**
 * Created by randheercode
 * Date: 5/11/20
 * Time: 2:51 pm
 */
class BinaryTreeMaximumPathSum {
    var max = Int.MIN_VALUE

    private fun calculateMax(node: TreeNode?): Int {
        if (node == null) return 0

        // max sum on the left and right sub-trees of node
        val leftMax = maxOf(calculateMax(node.left), 0)
        val rightMax = maxOf(calculateMax(node.right), 0)

        // the price to start a new path where `node` is a highest node
        val price = node.`val` + leftMax + rightMax

        // update max_sum if it's better to start a new path
        max = maxOf(max, price)

        // for recursion :
        // return the max gain if continue the same path
        return node.`val` + maxOf(leftMax, rightMax)
    }

    fun maxPathSum(root: TreeNode?): Int {
        calculateMax(root)
        return max
    }
}