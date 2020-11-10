package problem.facebook.fb_1

import kotlin.math.abs

/**
 * Created by randheercode
 * Date: 10/11/20
 * Time: 10:24 am
 */
class ClosestBinarySearchTreeValue {
    var res = -1
    var diff = Double.MAX_VALUE

    private fun decideClosest(root: TreeNode?, target: Double) {
        if (root == null) return
        val diffNow = abs(root.`val` - target)
        if (diffNow < diff) {
            diff = diffNow
            res = root.`val`
        }
        decideClosest(root.left, target)
        decideClosest(root.right, target)
    }

    fun closestValue(root: TreeNode?, target: Double): Int {
        decideClosest(root, target)
        return res
    }
}