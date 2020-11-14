package problem.facebook.fb_1

import java.util.*


/**
 * Created by randheercode
 * Date: 14/11/20
 * Time: 11:36 am
 */
class MergeKSortedLists {

    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    fun mergeKLists(lists: Array<ListNode?>): ListNode? {
        val q: Queue<ListNode> = PriorityQueue() { o1, o2 -> o1.`val` - o2.`val` }
        for (l in lists) {
            if (l != null) {
                q.add(l)
            }
        }
        val head = ListNode(0)
        var point: ListNode? = head
        while (!q.isEmpty()) {
            point?.next = q.poll()
            point = point?.next
            val next: ListNode? = point?.next
            if (next != null) {
                q.add(next)
            }
        }
        return head.next
    }
}