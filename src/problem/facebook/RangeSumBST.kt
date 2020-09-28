package problem.facebook

class RangeSumBST {
    fun rangeSumBST(root: TreeNode?, L: Int, R: Int): Int {
        fun sum(curr: TreeNode?): Int {
            if (curr == null) return 0
            return (if (curr.`val` in L..R) curr.`val` else 0) +
                    sum(curr.left) + sum(curr.right)
        }
        return sum(root)
    }

    fun rangeSumBSTOptimal(root: TreeNode?, L: Int, R: Int): Int {
        var sum = 0
        fun dfs(curr: TreeNode?) {
            if (curr == null) return
            if (curr.`val` in L..R) sum += curr.`val`
            if (curr.`val` > L) dfs(curr.left)
            if (curr.`val` < R) dfs(curr.right)
        }
        dfs(root)
        return sum
    }
}

fun main() {
    val treeNode = Helper.getTreeFromArray(arrayOf(10, 5, 15, 3, 7, null, 18))
    println(RangeSumBST().rangeSumBST(treeNode, 7, 15))
    println(RangeSumBST().rangeSumBSTOptimal(treeNode, 7, 15))
}