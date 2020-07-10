package ds

import utils.TreeNode
import java.util.*
import java.util.LinkedList


/**
 * Created by randheercode
 * Date: 2/7/20
 * Time: 3:46 pm
 * https://leetcode.com/explore/learn/card/data-structure-tree/
 */
class BinaryTree {

    // region PreOrder Traversal
    fun preorderTraversalRecursive(root: TreeNode?): List<Int> {
        val result = mutableListOf<Int>()
        fun traverse(root: TreeNode?) {
            if (root == null) return
            result.add(root.`val`)
            traverse(root.left)
            traverse(root.right)
        }
        traverse(root)
        return result
    }

    fun preorderTraversalIterative(root: TreeNode?): List<Int> {
        val result = mutableListOf<Int>()
        val stack = Stack<TreeNode>()
        if (root == null) return result
        stack.push(root)
        while (stack.size > 0) {
            val node = stack.peek()
            result.add(node.`val`)
            stack.pop()
            if (node.right != null) stack.push(node.right)
            if (node.left != null) stack.push(node.left)
        }
        return result
    }

    // endregion

    // region InOrder Traversal

    fun inorderTraversalRecursive(root: TreeNode?): List<Int> {
        val result = mutableListOf<Int>()
        fun traverse(root: TreeNode?) {
            if (root == null) return
            traverse(root.left)
            result.add(root.`val`)
            traverse(root.right)
        }
        traverse(root)
        return result
    }

    fun inorderTraversalIterative(root: TreeNode?): List<Int> {
        val result = mutableListOf<Int>()
        val stack = Stack<TreeNode>()
        var curr = root
        while (curr != null || stack.size > 0) {
            while (curr != null) {
                stack.add(curr)
                curr = curr.left
            }
            curr = stack.pop()
            result.add(curr.`val`)
            curr = curr.right
        }
        return result
    }

    //endregion

    // region PostOrder Traversal

    fun postOrderTraversalRecursive(root: TreeNode?): List<Int> {
        val result = mutableListOf<Int>()
        fun traverse(root: TreeNode?) {
            if (root == null) return
            traverse(root.left)
            traverse(root.right)
            result.add(root.`val`)
        }
        traverse(root)
        return result
    }

    fun postOrderTraversalIterative(root: TreeNode?): List<Int> {
        val result = mutableListOf<Int>()
        val stack = Stack<TreeNode>()
        if (root == null) return result
        stack.push(root)
        while (stack.size > 0) {
            val node = stack.peek()
            result.add(0, node.`val`)
            stack.pop()
            if (node.left != null) stack.push(node.left)
            if (node.right != null) stack.push(node.right)
        }
        return result
    }

    // endregion

    // region LevelOrder Traversal

    fun levelOrderTraversalRecursive(root: TreeNode?): List<List<Int>> {
        fun height(root: TreeNode?): Int {
            return if (root == null) 0 else (1 + maxOf(height(root.left), height(root.right)))
        }

        val height = height(root)
        val result = mutableListOf<MutableList<Int>>()
        repeat(height) { result.add(mutableListOf()) }

        fun traverse(root: TreeNode?, label: Int) {
            if (root == null) return
            result[label].add(root.`val`)
            traverse(root.left, label + 1)
            traverse(root.right, label + 1)
        }
        traverse(root, 0)
        return result
    }

    fun levelOrderTraversalIterative(root: TreeNode?): List<List<Int>> {
        val result = mutableListOf<MutableList<Int>>()
        var currentResult = mutableListOf<Int>()
        if (root == null) return result
        val current = LinkedList<TreeNode>()
        val next = LinkedList<TreeNode>()
        current.add(root)
        while (current.size > 0) {
            val node = current.pop()
            if (node.left != null) next.add(node.left!!)
            if (node.right != null) next.add(node.right!!)
            currentResult.add(node.`val`)
            if (current.isEmpty()) {
                result.add(currentResult)
                currentResult = mutableListOf()
                if (next.isNotEmpty()) {
                    current.addAll(next)
                    next.clear()
                }
            }
        }

        return result
    }

    // endregion

    // region Problems

    fun maxDepth(root: TreeNode?): Int {
        if (root == null) return 0
        return 1 + maxOf(maxDepth(root.left), maxDepth(root.right))
    }


    fun isSymmetric(root: TreeNode?): Boolean {
        fun isMirror(t1: TreeNode?, t2: TreeNode?): Boolean {
            if (t1 == null && t2 == null) return true
            return if (t1 == null || t2 == null) false else t1.`val` == t2.`val`
                    && isMirror(t1.right, t2.left)
                    && isMirror(t1.left, t2.right)
        }
        return isMirror(root, root)
    }

    fun hasPathSum(root: TreeNode?, sum: Int): Boolean {
        var sum = sum
        if (root == null) return false
        sum -= root.`val`
        return if (root.left == null && root.right == null) sum == 0
        else hasPathSum(root.left, sum) || hasPathSum(root.right, sum)
    }

    fun countUnivalSubtrees(root: TreeNode?): Int {
        if (root == null) return 0
        var count = 0
        fun count(node: TreeNode?): Boolean {
            if (node?.left == null && node?.right == null) {
                count++
                return true
            }
            var isUnval = true

            if (node.left != null) {
                isUnval = count(node.left) && isUnval && node.left?.`val` == node.`val`
            }

            if (node.right != null) {
                isUnval = count(node.right) && isUnval && node.right?.`val` == node.`val`
            }

            if (!isUnval) return false
            count++
            return true
        }
        count(root)
        return count
    }

    fun buildTreeInPost(inorder: IntArray, postorder: IntArray): TreeNode? {
        var postOrderPos = postorder.size - 1
        val indexMap = mutableMapOf<Int, Int>()
        fun helper(inOrderLeft: Int, inOrderRight: Int): TreeNode? {
            if (inOrderLeft > inOrderRight) return null
            val rootVal = postorder[postOrderPos]
            val root = TreeNode(rootVal)
            val index = indexMap[rootVal]!!
            postOrderPos--
            root.right = helper(index + 1, inOrderRight)
            root.left = helper(inOrderLeft, index - 1)
            return root
        }

        var idx = 0
        for (`val` in inorder) indexMap[`val`] = idx++
        return helper(0, inorder.size - 1)
    }

    fun buildTreeInPre(preorder: IntArray, inorder: IntArray): TreeNode? {
        var preOrderPos = 0
        val indexMap = mutableMapOf<Int, Int>()
        fun helper(inOrderLeft: Int, inOrderRight: Int): TreeNode? {
            if (inOrderLeft > inOrderRight) return null
            val rootVal = preorder[preOrderPos]
            val root = TreeNode(rootVal)
            val index = indexMap[rootVal]!!
            preOrderPos++
            root.left = helper(inOrderLeft, index - 1)
            root.right = helper(index + 1, inOrderRight)
            return root
        }

        var idx = 0
        for (`val` in inorder) indexMap[`val`] = idx++
        return helper(0, inorder.size - 1)
    }

    // https://leetcode.com/explore/learn/card/data-structure-tree/133/conclusion/994/
    inner class Node(var `val`: Int) {
        var left: Node? = null
        var right: Node? = null
        var next: Node? = null
    }

    fun connect(root: Node?): Node? {
        if (root == null) {
            return root
        }
        val nodes = LinkedList<Node>()
        nodes.add(root)
        while (nodes.size > 0) {
            val size = nodes.size
            for (i in 0 until size) {
                val node = nodes.poll()
                if (i < size - 1) {
                    node.next = nodes.peek()
                }
                if (node.left != null) {
                    nodes.add(node.left!!)
                }
                if (node.right != null) {
                    nodes.add(node.right!!)
                }
            }
        }
        return root
    }

    fun connect2(root: Node?): Node? {
        var prev: Node? = null
        var leftmost: Node? = null
        fun processChild(childNode: Node?) {
            if (childNode != null) {
                if (prev != null) {
                    prev?.next = childNode
                } else {
                    leftmost = childNode
                }
                prev = childNode
            }
        }

        if (root == null) {
            return root
        }
        leftmost = root
        var curr: Node? = leftmost
        while (leftmost != null) {
            prev = null
            curr = leftmost
            leftmost = null
            while (curr != null) {
                processChild(curr.left)
                processChild(curr.right)
                curr = curr.next
            }
        }
        return root
    }

    fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
        var ans: TreeNode? = null
        fun recurseTree(currentNode: TreeNode?, p1: TreeNode?, q1: TreeNode?): Boolean {
            if (currentNode == null) {
                return false
            }
            val left = if (recurseTree(currentNode.left, p1, q1)) 1 else 0
            val right = if (recurseTree(currentNode.right, p1, q1)) 1 else 0
            val mid = if (currentNode === p1 || currentNode === q1) 1 else 0
            if (mid + left + right >= 2) {
                ans = currentNode
            }
            return mid + left + right > 0
        }
        recurseTree(root, p, q)
        return ans
    }
    // endregion

}

class BinaryTreeCodec {
    // Encodes a tree to a single string.
    fun serialize(root: TreeNode?): String {
        if (root == null) return "[]"
        val queue: Queue<TreeNode?> = LinkedList<TreeNode>()
        queue.add(root)
        var sol = "["
        while (!queue.isEmpty()) {
            val temp = queue.remove()
            if (temp != null) {
                queue.add(temp.left)
                queue.add(temp.right)
                sol += temp.`val`
            } else sol += "null"
            sol += ","
        }
        var j = sol.length - 1
        while (j >= 0 && !Character.isDigit(sol[j])) {
            j--
        }
        sol = sol.substring(0, j + 1)
        sol += "]"
        return sol
    }

    // Decodes your encoded data to tree.
    fun deserialize(data: String): TreeNode? {
        if (data.length == 2) return null
        val nodes = data.substring(1, data.length - 1).split(",".toRegex()).toTypedArray()
        val queue: Queue<TreeNode?> = LinkedList<TreeNode>()
        val sol = TreeNode(Integer.valueOf(nodes[0]))
        queue.add(sol)
        var j = 1
        while (j < nodes.size) {
            var left: TreeNode? = null
            var right: TreeNode? = null
            val temp = queue.remove()
            if (nodes[j] != "null") left = TreeNode(Integer.valueOf(nodes[j]))
            j++
            if (j < nodes.size && nodes[j] != "null") right = TreeNode(Integer.valueOf(nodes[j]))
            j++
            temp!!.left = left
            temp.right = right
            if (left != null) queue.add(left)
            if (right != null) queue.add(right)
        }
        return sol
    }
}
