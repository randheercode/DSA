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

}
