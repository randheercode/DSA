package problem.old

import utils.TreeNode

/**
 * Created by randheercode
 * Date: 24/5/20
 * Time: 10:33 pm
 * Problem Statement: Return the root node of a binary search tree that matches the given preorder traversal.
 */
class BstFromPreOrder {
    fun bstFromPreorder(preorder: IntArray): TreeNode? {
        if (preorder.isEmpty()) return null
        fun addNode(root: TreeNode?, value: Int) {
            if (root == null) return
            if (value < root.`val`) {
                if (root.left != null) addNode(root.left, value) else root.left = TreeNode(value)
            } else {
                if (root.right != null) addNode(root.right, value) else root.right = TreeNode(value)
            }
        }

        val root: TreeNode? = TreeNode(preorder[0])
        for (i in 1 until preorder.size) addNode(root, preorder[i])
        return root
    }

    fun bstFromPreorderAnother(preorder: IntArray): TreeNode? {
        fun constructBST(preorder: IntArray, start: Int, end: Int): TreeNode? {
            if (start >= end || start == preorder.size) {
                return null
            }
            val root = TreeNode(preorder[start])
            var i = start + 1
            while (i < end && preorder[start] > preorder[i]) {
                i++
            }
            root.left = constructBST(preorder, start + 1, i)
            root.right = constructBST(preorder, i, end)
            return root
        }
        return constructBST(preorder, 0, preorder.size)
    }
}