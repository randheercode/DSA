package problem.challenge

class Dec20 {
    private fun reverseLinkedList(head: ListNode?): ListNode? {
        val result = ListNode(0)
        var curr = head
        while (curr != null) {
            val temp = result.next
            result.next = curr
            val next = curr.next
            curr.next = temp
            curr = next
        }
        return result.next
    }

    fun plusOne(head: ListNode?): ListNode? {
        if (head == null) return null
        val reversed = reverseLinkedList(head)
        var curr = reversed
        var carry = 1
        while (curr != null && carry > 0) {
            curr.`val` += carry
            carry = curr.`val`.div(10)
            curr.`val` = curr.`val`.rem(10)
            curr = curr.next
        }
        val result = reverseLinkedList(reversed)
        if (carry > 0) {
            val newNode = ListNode(carry)
            newNode.next = result
            return newNode
        }
        return result
    }

    fun sortedSquares(nums: IntArray): IntArray {
        val result = IntArray(nums.size)
        var start = 0
        var end = nums.lastIndex
        var idx = nums.lastIndex
        while (start <= end) {
            val fromStart = nums[start] * nums[start]
            val fromEnd = nums[end] * nums[end]

            result[idx--] = if (fromEnd >= fromStart) {
                end--
                fromEnd
            } else {
                start++
                fromStart
            }
        }
        return result
    }
}