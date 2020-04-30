package problems

/**
 * Created by randheercode
 * Date: 30/4/20
 * Time: 7:35 pm
 * Statement: Given a binary tree where each path going from the root to any leaf form a valid sequence,
 * check if a given string is a valid sequence in such binary tree.
 * class TreeNode(var `val`: Int) {
 *      var left: TreeNode? = null
 *      var right: TreeNode? = null
 * }
 */
class BinaryTreeValidPath {
    fun isValidSequence(root: TreeNode?, arr: IntArray): Boolean {
        return findPath(root, arr, 0)
    }

    private fun findPath(root: TreeNode?, arr: IntArray, index: Int): Boolean {
        if (root?.`val` != arr[index]) return false
        return if (index == arr.lastIndex) root.left == null && root.right == null
        else findPath(root.left, arr, index.plus(1)) || findPath(root.right, arr, index.plus(1))
    }
}

fun main() {
    val root = TreeNode(0)
    root.right = TreeNode(0)
    root.right?.left = TreeNode(0)
    root.left = TreeNode(1)
    root.left?.right = TreeNode(1)
    root.left?.right?.left = TreeNode(0)
    root.left?.right?.right = TreeNode(0)
    root.left?.left = TreeNode(0)
    root.left?.left?.right = TreeNode(1)
    println(BinaryTreeValidPath().isValidSequence(root, intArrayOf(0, 1, 0, 1)))
}
