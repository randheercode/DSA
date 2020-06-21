package ds

import problems.ListNode

/**
 * Created by randheercode
 * Date: 21/6/20
 * Time: 12:37 pm
 * https://leetcode.com/explore/learn/card/linked-list/
 */

class MyLinkedList {
    inner class ListNode internal constructor(x: Int) {
        var `val`: Int = x
        var next: ListNode? = null
        var prev: ListNode? = null
    }

    var size = 0

    // sentinel nodes as pseudo-head and pseudo-tail
    var head: ListNode
    var tail: ListNode

    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1.  */
    operator fun get(index: Int): Int {
        // if index is invalid
        if (index < 0 || index >= size) return -1

        // choose the fastest way: to move from the head
        // or to move from the tail
        var curr: ListNode? = head
        if (index + 1 < size - index) for (i in 0 until index + 1) curr = curr?.next else {
            curr = tail
            for (i in 0 until size - index) curr = curr?.prev
        }
        return curr?.`val` ?: -1
    }

    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.  */
    fun addAtHead(`val`: Int) {
        val pred = head
        val succ = head.next
        ++size
        val toAdd = ListNode(`val`)
        toAdd.prev = pred
        toAdd.next = succ
        pred.next = toAdd
        succ?.prev = toAdd
    }

    /** Append a node of value val to the last element of the linked list.  */
    fun addAtTail(`val`: Int) {
        val succ = tail
        val pred = tail.prev
        ++size
        val toAdd = ListNode(`val`)
        toAdd.prev = pred
        toAdd.next = succ
        pred?.next = toAdd
        succ.prev = toAdd
    }

    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted.  */
    fun addAtIndex(index: Int, `val`: Int) {
        // If index is greater than the length,
        // the node will not be inserted.
        var index = index
        if (index > size) return

        // [so weird] If index is negative,
        // the node will be inserted at the head of the list.
        if (index < 0) index = 0

        // find predecessor and successor of the node to be added
        var pred: ListNode?
        var succ: ListNode?
        if (index < size - index) {
            pred = head
            for (i in 0 until index) pred = pred?.next
            succ = pred!!.next
        } else {
            succ = tail
            for (i in 0 until size - index) succ = succ?.prev
            pred = succ?.prev
        }

        // insertion itself
        ++size
        val toAdd = ListNode(`val`)
        toAdd.prev = pred
        toAdd.next = succ
        pred!!.next = toAdd
        succ!!.prev = toAdd
    }

    /** Delete the index-th node in the linked list, if the index is valid.  */
    fun deleteAtIndex(index: Int) {
        // if the index is invalid, do nothing
        if (index < 0 || index >= size) return

        // find predecessor and successor of the node to be deleted
        var pred: ListNode?
        var succ: ListNode?
        if (index < size - index) {
            pred = head
            for (i in 0 until index) pred = pred?.next
            succ = pred!!.next!!.next
        } else {
            succ = tail
            for (i in 0 until size - index - 1) succ = succ?.prev
            pred = succ!!.prev!!.prev
        }

        // delete pred.next
        --size
        pred?.next = succ
        succ?.prev = pred
    }

    init {
        head = ListNode(0)
        tail = ListNode(0)
        head.next = tail
        tail.prev = head
    }
}

class LinkedList {
    fun hasCycle(head: ListNode?): Boolean {
        if (head == null) return false
        var fast = head
        var slow = head

        while (fast?.next != null) {
            fast = fast.next?.next
            slow = slow?.next

            if (fast == slow) return true
        }

        return false
    }


    fun detectCycle(head: ListNode?): ListNode? {
        fun getIntersect(head: ListNode): ListNode? {
            var tortoise: ListNode? = head
            var hare: ListNode? = head

            while (hare?.next != null) {
                tortoise = tortoise?.next
                hare = hare.next?.next
                if (tortoise == hare) {
                    return tortoise
                }
            }
            return null
        }

        if (head == null) {
            return null
        }

        val intersect = getIntersect(head) ?: return null

        var ptr1 = head
        var ptr2: ListNode? = intersect
        while (ptr1 != ptr2) {
            ptr1 = ptr1?.next
            ptr2 = ptr2?.next
        }
        return ptr1
    }

    fun getIntersectionNode(headA: ListNode?, headB: ListNode?): ListNode? {
        val first = mutableSetOf<ListNode>()
        var firstHead = headA
        while (firstHead != null) {
            first.add(firstHead)
            firstHead = firstHead.next
        }
        var secondHead = headB
        while (secondHead != null) {
            if (first.contains(secondHead)) return secondHead
            secondHead = secondHead?.next
        }
        return null
    }
    fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
        val dummy = ListNode(0)
        dummy.next = head
        var first: ListNode? = dummy
        var second: ListNode? = dummy
        // Advances first pointer so that the gap between first and second is n nodes apart
        for (i in 1..n + 1) {
            first = first?.next
        }
        // Move first to the end, maintaining the gap
        while (first != null) {
            first = first.next
            second = second!!.next
        }
        second!!.next = second.next?.next
        return dummy.next
    }
}

fun main() {

}