package problem.facebook.fb_1

import java.util.*

/**
 * Created by randheercode
 * Date: 8/11/20
 * Time: 12:49 pm
 */
class Node(var `val`: Int) {
    var left: Node? = null
    var right: Node? = null
}

class BSTToDLinkList {

    private val stack = Stack<Node>()
    private var head: Node? = Node(0)
    private var current: Node? = head

    private fun iteratorHelper(root: Node?) {
        var node = root
        while (node != null) {
            stack.push(node)
            node = node.left
        }
    }


    fun treeToDoublyList(root: Node?): Node? {
        iteratorHelper(root)
        while (stack.isNotEmpty()) {
            val node = stack.pop()
            if (node.right != node) {
                iteratorHelper(node.right)
            }
            current?.right = node
            node.left = current
            current = current?.right
        }

        head = head?.right

        head?.left = current
        current?.right = head

        return head
    }
}