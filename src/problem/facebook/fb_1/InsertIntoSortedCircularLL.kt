package problem.facebook.fb_1

/**
 * Created by randheercode
 * Date: 12/11/20
 * Time: 11:47 am
 */


class InsertIntoSortedCircularLL {
    class Node(var `val`: Int) {
        var next: Node? = null
    }

    fun insert(head: Node?, insertVal: Int): Node? {
        if (head == null) {
            val newNode = Node(insertVal)
            newNode.next = newNode
            return newNode
        }
        var prev = head
        var curr = head.next
        var toInsert = false
        do {
            if (prev!!.`val` <= insertVal && insertVal <= curr!!.`val`) {
                // Case 1).
                toInsert = true
            } else if (prev.`val` > curr!!.`val`) {
                // Case 2).
                if (insertVal >= prev.`val` || insertVal <= curr.`val`) toInsert = true
            }
            if (toInsert) {
                prev.next = Node(insertVal)
                prev.next?.next = curr
                return head
            }
            prev = curr
            curr = curr.next
        } while (prev != head)

        // Case 3).
        prev.next = Node(insertVal)
        prev.next?.next = curr
        return head
    }
}