package problem.facebook.fb_1

import org.junit.Assert
import org.junit.Test

/**
 * Created by randheercode
 * Date: 3/11/20
 * Time: 11:15 am
 */
class KthLargestElementInAnArrayTest {
    private val obj = KthLargestElementInAnArray()

    @Test
    fun findKthLargest() {
        Assert.assertEquals(5, obj.findKthLargest(intArrayOf(3, 2, 1, 5, 6, 4), 2))
        Assert.assertEquals(4, obj.findKthLargest(intArrayOf(3, 2, 3, 1, 2, 4, 5, 5, 6), 4))
    }
}