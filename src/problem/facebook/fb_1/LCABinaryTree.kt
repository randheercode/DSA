package problem.facebook.fb_1

import java.util.*
import kotlin.collections.set


/**
 * Created by randheercode
 * Date: 18/11/20
 * Time: 8:56 am
 */
class LCABinaryTree {
    fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {

        // Stack for tree traversal
        var p = p
        var q = q
        val stack: Deque<TreeNode> = LinkedList()

        // HashMap for parent pointers
        val parent: MutableMap<TreeNode?, TreeNode?> = HashMap()
        parent[root] = null
        stack.push(root)

        // Iterate until we find both the nodes p and q
        while (!parent.containsKey(p) || !parent.containsKey(q)) {
            val node = stack.pop()

            // While traversing the tree, keep saving the parent pointers.
            if (node!!.left != null) {
                parent[node.left] = node
                stack.push(node.left)
            }
            if (node.right != null) {
                parent[node.right] = node
                stack.push(node.right)
            }
        }

        // Ancestors set() for node p.
        val ancestors: MutableSet<TreeNode?> = HashSet()

        // Process all ancestors for node p using parent pointers.
        while (p != null) {
            ancestors.add(p)
            p = parent[p]
        }

        // The first ancestor of q which appears in
        // p's ancestor set() is their lowest common ancestor.
        while (!ancestors.contains(q)) q = parent[q]
        return q
    }
}