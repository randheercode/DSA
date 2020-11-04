package problem.facebook.fb_1

import org.junit.Assert
import org.junit.Test


/**
 * Created by randheercode
 * Date: 4/11/20
 * Time: 4:48 pm
 */
class ContinuousSubarraySumTest {
    private val obj = ContinuousSubarraySum()

    @Test
    fun checkSubarraySum() {
        Assert.assertTrue(obj.checkSubarraySum(intArrayOf(23, 2, 4, 6, 7), 6))
        Assert.assertTrue(obj.checkSubarraySum(intArrayOf(23, 2, 6, 4, 7), 6))
    }
}