package problem.facebook.fb_1

/**
 * Created by randheercode
 * Date: 15/11/20
 * Time: 11:42 am
 */
class CopyListRandomPointer {
    class Node(var `val`: Int) {
        var next: Node? = null
        var random: Node? = null
    }

    fun copyRandomList(head: Node?): Node? {
        if (head == null) {
            return null
        }

        // Creating a new weaved list of original and copied nodes.
        var ptr = head
        while (ptr != null) {

            // Cloned node
            val newNode = Node(ptr.`val`)

            // Inserting the cloned node just next to the original node.
            // If A->B->C is the original linked list,
            // Linked list after weaving cloned nodes would be A->A'->B->B'->C->C'
            newNode.next = ptr.next
            ptr.next = newNode
            ptr = newNode.next
        }
        ptr = head

        // Now link the random pointers of the new nodes created.
        // Iterate the newly created list and use the original nodes' random pointers,
        // to assign references to random pointers for cloned nodes.
        while (ptr != null) {
            ptr.next?.random = if (ptr.random != null) ptr.random?.next else null
            ptr = ptr.next?.next
        }

        // Unweave the linked list to get back the original linked list and the cloned list.
        // i.e. A->A'->B->B'->C->C' would be broken to A->B->C and A'->B'->C'
        var ptr_old_list = head // A->B->C
        var ptr_new_list = head.next // A'->B'->C'
        val head_old = head.next
        while (ptr_old_list != null) {
            ptr_old_list.next = ptr_old_list.next?.next
            ptr_new_list?.next = if (ptr_new_list?.next != null) ptr_new_list.next?.next else null
            ptr_old_list = ptr_old_list.next
            ptr_new_list = ptr_new_list?.next
        }
        return head_old
    }
}