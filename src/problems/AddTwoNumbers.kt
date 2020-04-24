package problems

/**
 * Created by randheercode
 * Date: 8/4/20
 * Time: 7:26 pm
 * Problem Statement: You are given two non-empty linked lists representing two non-negative integers. The digits are
 * stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 * References: https://leetcode.com/problems/add-two-numbers/
 */

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

class AddTwoNumbers {

    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {

        var first = l1
        var second = l2
        var carryForward = 0

        var head: ListNode? = null
        var next: ListNode? = null

        do {
            val sum = (first?.`val` ?: 0) + (second?.`val` ?: 0) + carryForward
            val sumCurrent = sum.rem(10)
            carryForward = sum.div(10)
            if (head == null) {
                head = ListNode(sumCurrent)
                next = head
            } else {
                next?.next = ListNode(sumCurrent)
                next = next?.next
            }

            first = first?.next
            second = second?.next
        } while (first != null && second != null)

        if (first != null) {
            while (first != null) {
                val sum = (first?.`val` ?: 0) + carryForward
                val sumCurrent = sum.rem(10)
                carryForward = sum.div(10)
                next?.next = ListNode(sumCurrent)
                next = next?.next
                first = first.next
            }
        }

        if (second != null) {
            while (second != null) {
                val sum = (second?.`val` ?: 0) + carryForward
                val sumCurrent = sum.rem(10)
                carryForward = sum.div(10)
                next?.next = ListNode(sumCurrent)
                next = next?.next
                second = second.next
            }
        }
        if (carryForward != 0) {
            next?.next = ListNode(carryForward)
        }

        return head

    }

}