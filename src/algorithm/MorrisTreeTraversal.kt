/**
 * Created by IntelliJ IDEA.
 * User: randheercode
 * Date: 8/4/20
 * Time: 7:52 pm
 * Morris Tree Traversal: An efficient tree traversal mechanism.
 */
class Node(var data: Int) {
    var left_node: Node? = null
    var right_node: Node? = null

}

class Tree {
    var root: Node? = null
}

class MorrisTreeTraversal {
    fun morrisTraversal(root: Node?) {
        var curr: Node?
        var prev: Node?
        if (root == null) return
        curr = root
        while (curr != null) {
            if (curr.left_node == null) {
                print(curr.data.toString() + " ")
                curr = curr.right_node
            } else {
                /* Find the previous (prev) of curr */
                prev = curr.left_node
                while (prev?.right_node != null && prev.right_node !== curr) prev = prev.right_node

                /* Make curr as right child of its prev */
                if (prev?.right_node == null) {
                    prev?.right_node = curr
                    curr = curr.left_node
                } else {
                    prev.right_node = null
                    print(curr.data.toString() + " ")
                    curr = curr.right_node
                }
            }
        }
    }
}

fun main(args: Array<String>) {
    val tree = Tree()
    tree.root = Node(4)
    tree.root?.left_node = Node(2)
    tree.root?.right_node = Node(5)
    tree.root?.left_node?.left_node = Node(1)
    tree.root?.left_node?.right_node = Node(3)
    MorrisTreeTraversal().morrisTraversal(tree.root)
}