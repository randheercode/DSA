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

}

