package problem.facebook.fb_1

/**
 * Created by randheercode
 * Date: 18/11/20
 * Time: 11:32 am
 */
class LargestBSTSubtree {
    fun largestBSTSubtree(root: TreeNode?): Int {
        return if (isBST(root)) count(root) else {
            Math.max(largestBSTSubtree(root!!.left), largestBSTSubtree(root.right))
        }
    }

    private fun isBST(root: TreeNode?): Boolean {
        return BST(root, Int.MIN_VALUE, Int.MAX_VALUE)
    }

    private fun BST(root: TreeNode?, min: Int, max: Int): Boolean {
        return if (root == null) true else if (root.`val` >= max || root.`val` <= min) false else BST(root.left, min, root.`val`) && BST(root.right, root.`val`, max)
    }

    fun count(root: TreeNode?): Int {
        return if (root == null) 0 else 1 + count(root.left) + count(root.right)
    }
}