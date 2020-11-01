package problem.facebook.fb_1

import org.junit.Assert
import org.junit.Test

/**
 * Created by randheercode
 * Date: 1/11/20
 * Time: 9:44 pm
 */
class SubarraySumEqualsKTest {
    private val obj = SubarraySumEqualsK()

    @Test
    fun subarraySum() {
        Assert.assertEquals(2, obj.subarraySum(intArrayOf(1, 1, 1), 2))
        Assert.assertEquals(2, obj.subarraySum(intArrayOf(1, 2, 3), 3))
        Assert.assertEquals(3, obj.subarraySum(intArrayOf(1, -1, 0), 0))
    }
}