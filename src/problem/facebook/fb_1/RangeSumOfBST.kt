package problem.facebook.fb_1

/**
 * Created by randheercode
 * Date: 5/11/20
 * Time: 2:59 pm
 */
class RangeSumOfBST {
    var sum = 0
    fun rangeSumBST(root: TreeNode?, L: Int, R: Int): Int {
        calculateSum(root, L, R)
        return sum
    }

    private fun calculateSum(root: TreeNode?, l: Int, r: Int) {
        if (root == null) return
        when {
            root.`val` in l..r -> {
                sum += root.`val`
                calculateSum(root.left, l, r)
                calculateSum(root.right, l, r)
            }
            root.`val` > r -> {
                calculateSum(root.left, l, r)
            }
            root.`val` < l -> {
                calculateSum(root.right, l, r)
            }
        }
    }
}