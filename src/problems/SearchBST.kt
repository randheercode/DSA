package problems

import utils.TreeNode

/**
 * Created by randheercode
 * Date: 3/6/20
 * Time: 11:08 am
 * Problem Statement: Search BST.
 */
class SearchBST {
    fun searchBST(root: TreeNode?, `val`: Int): TreeNode? {
        if (root == null) return null
        if (root.`val` == `val`) return root
        return if (root.`val` > `val`) searchBST(root.right, `val`)
        else searchBST(root.left, `val`)
    }
}

fun main() {
    SearchBST().searchBST(TreeNode(5).apply {
        left = TreeNode(4)
        right = TreeNode(6)
    }, 5)
}