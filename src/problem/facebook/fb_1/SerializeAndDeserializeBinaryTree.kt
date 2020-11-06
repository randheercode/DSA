package problem.facebook.fb_1

import java.util.*


/**
 * Created by randheercode
 * Date: 6/11/20
 * Time: 3:14 pm
 */
class SerializeAndDeserializeBinaryTree {
    // Encodes a tree to a single string.
    fun serialize(root: TreeNode?): String {
        val sb = StringBuilder()
        val q: Queue<TreeNode?> = LinkedList()
        q.add(root)
        while (!q.isEmpty()) {
            val first = q.poll()
            sb.append((first?.`val` ?: "null").toString() + ",")
            if (first != null) {
                q.add(first.left)
                q.add(first.right)
            }
        }
        return sb.toString()
    }

    // Decodes your encoded data to tree.
    fun deserialize(data: String): TreeNode? {
        val nodes = data.split(",".toRegex()).toTypedArray()
        val q: Queue<TreeNode?> = LinkedList()
        val root = getTreeNode(nodes[0])
        q.add(root)
        var i = 1
        while (!q.isEmpty()) {
            val first = q.poll()
            if (first != null) {
                first.left = getTreeNode(nodes[i++])
                first.right = getTreeNode(nodes[i++])
                if (first.left != null) q.add(first.left)
                if (first.right != null) q.add(first.right)
            }
        }
        return root
    }

    private fun getTreeNode(`val`: String): TreeNode? {
        return if (`val` == "null") null else TreeNode(Integer.valueOf(`val`))
    }
}