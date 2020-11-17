package problem.facebook.fb_1

import java.util.*


/**
 * Created by randheercode
 * Date: 17/11/20
 * Time: 4:58 pm
 */
class SortedListToBST() {
    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    private var values: MutableList<Int>? = null

    init {
        values = ArrayList()
    }

    private fun mapListToValues(head: ListNode) {
        var head: ListNode? = head
        while (head != null) {
            values!!.add(head.`val`)
            head = head.next
        }
    }

    private fun convertListToBST(left: Int, right: Int): TreeNode? {
        // Invalid case
        if (left > right) {
            return null
        }

        // Middle element forms the root.
        val mid = (left + right) / 2
        val node = TreeNode(values!![mid])

        // Base case for when there is only one element left in the array
        if (left == right) {
            return node
        }

        // Recursively form BST on the two halves
        node.left = convertListToBST(left, mid - 1)
        node.right = convertListToBST(mid + 1, right)
        return node
    }

    fun sortedListToBST(head: ListNode): TreeNode? {

        // Form an array out of the given linked list and then
        // use the array to form the BST.
        mapListToValues(head)

        // Convert the array to
        return convertListToBST(0, values!!.size - 1)
    }
}