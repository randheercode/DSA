package problem.old

/**
 * Created by randheercode
 * Date: 16/5/20
 * Time: 6:27 pm
 * Given a singly linked list, group all odd nodes together followed by the even nodes. Please note here we are talking about the node number and not the value in the nodes.
 * You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.
 */
class OddEvenLinkedList {
    class Solution {
        fun oddEvenList(head: ListNode?): ListNode? {
            if (head?.next == null) return head
            val oddHead = ListNode(0)
            val evenHead = ListNode(0)
            var oddEnd: ListNode? = oddHead
            var evenEnd: ListNode? = evenHead

            var ptr = head
            var index = 1
            while (ptr != null) {
                if (index % 2 == 0) {
                    evenEnd?.next = ptr
                    evenEnd = evenEnd?.next
                } else {
                    oddEnd?.next = ptr
                    oddEnd = oddEnd?.next
                }
                index++
                ptr = ptr.next
            }
            oddEnd?.next = evenHead.next
            evenEnd?.next = null
            return oddHead.next
        }
    }
}