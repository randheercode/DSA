package problems

/**
 * Created by randheercode
 * Date: 20/5/20
 * Time: 7:50 pm
 */
class KthSmallestBST {
    fun kthSmallest(root: TreeNode?, k: Int): Int {
        fun getKthInOrder(root: TreeNode?, k: Int, data: MutableList<Int>) {
            if (root == null) return
            if (data.size < k) getKthInOrder(root.left, k, data)
            if (data.size < k) data.add(root.`val`)
            if (data.size < k) getKthInOrder(root.right, k, data)
        }

        val data = mutableListOf<Int>()
        getKthInOrder(root, k, data)
        return data.last()
    }
}

fun main() {
    testCase1()
    testCase2()
}

fun testCase1() {
    val root = TreeNode(3)
    root.right = TreeNode(4)
    root.left = TreeNode(1)
    root.left?.right = TreeNode(2)
    for (i in 1..4) println(KthSmallestBST().kthSmallest(root, i))
}


fun testCase2() {
    val root = TreeNode(5)
    root.right = TreeNode(6)
    root.left = TreeNode(3)
    root.left?.right = TreeNode(4)
    root.left?.left = TreeNode(2)
    root.left?.left?.left = TreeNode(1)
    for (i in 1..6) println(KthSmallestBST().kthSmallest(root, i))
}
