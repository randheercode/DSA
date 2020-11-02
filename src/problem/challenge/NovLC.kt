package problem.challenge

import java.lang.StrictMath.pow

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

class NovLC {

    fun canAttendMeetings(intervals: Array<IntArray>): Boolean {
        intervals.sortBy { it[0] }
        for (i in 1 until intervals.size) {
            if (intervals[i - 1][1] > intervals[i][0]) return false
        }
        return true
    }

    fun getDecimalValue(head: ListNode?): Int {
        var count = 0
        var current = head
        while (current?.next != null) {
            current = current.next
            count++
        }
        var result = 0
        current = head
        while (current != null) {
            if (current.`val` == 1) {
                result += pow(2.0, count.toDouble()).toInt()
            }
            current = current.next
            count--
        }
        return result
    }

    fun getDecimalValueOptimal(head: ListNode?): Int {
        var head = head
        var num = head!!.`val`
        while (head?.next != null) {
            num = num shl 1 or head.next!!.`val`
            head = head.next
        }
        return num
    }

    fun insertionSortList(head: ListNode?): ListNode? {

        val resultHead = ListNode(Int.MIN_VALUE)
        var current = head

        while (current != null) {

            // Copy and Detach
            val temp = current
            current = current.next
            temp.next = null

            var sortedCurrent: ListNode? = resultHead
            while (sortedCurrent?.next != null && sortedCurrent.next!!.`val` <= temp.`val`) {
                sortedCurrent = sortedCurrent.next
            }
            val tempNext = sortedCurrent?.next
            sortedCurrent?.next = temp
            temp.next = tempNext
        }
        return resultHead.next
    }

}
