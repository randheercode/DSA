package problem.challenge

import generateIntArray
import org.junit.Assert
import org.junit.Test

class NovLCTest {

    private val obj = NovLC()

    @Test
    fun canAttendMeetings() {
        Assert.assertFalse(obj.canAttendMeetings(generateIntArray("[[0,30],[5,10],[15,20]]")))
        Assert.assertTrue(obj.canAttendMeetings(generateIntArray("[[7,10],[2,4]]")))
    }

    @Test
    fun getDecimalValue() {
        Assert.assertEquals(0, obj.getDecimalValue(ListNode(0)))
        Assert.assertEquals(1, obj.getDecimalValue(ListNode(1)))
    }

    @Test
    fun insertionSortList() {
        val head = ListNode(4)
        head.next = ListNode(2)
        head.next?.next = ListNode(1)
        head.next?.next?.next = ListNode(3)
        obj.insertionSortList(head)
        Assert.assertTrue(true)
    }

    @Test
    fun removeInterval() {
        val res = obj.removeInterval(generateIntArray("[[0,2],[3,4],[5,7]]"), intArrayOf(1, 6))
        Assert.assertEquals("[[0, 1], [6, 7]]", res.toString())
    }

}

