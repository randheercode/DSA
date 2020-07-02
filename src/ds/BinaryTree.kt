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

    // endregion

}
