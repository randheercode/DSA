package problem.facebook

object Helper {
    fun getTreeFromArray(array: Array<Int?>): TreeNode? {
        if (array.isEmpty()) return null
        fun createTree(idx: Int): TreeNode? {
            if (idx >= array.size || array[idx] == null) return null
            val node = TreeNode(array[idx]!!)
            node.left = createTree((2 * idx) + 1)
            node.right = createTree((2 * idx) + 2)
            return node
        }
        return createTree(0)
    }
}