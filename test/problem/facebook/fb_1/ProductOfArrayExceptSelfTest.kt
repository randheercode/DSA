package problem.facebook.fb_1

import org.junit.Assert
import org.junit.Test

/**
 * Created by randheercode
 * Date: 1/11/20
 * Time: 10:04 pm
 */
class ProductOfArrayExceptSelfTest {
    private val obj = ProductOfArrayExceptSelf()

    @Test
    fun subarraySum() {
        Assert.assertEquals("[24, 12, 8, 6]", obj.productExceptSelf(intArrayOf(1, 2, 3, 4)).toList().toString())
    }

    @Test
    fun subarraySumOptimal() {
        Assert.assertEquals("[24, 12, 8, 6]", obj.productExceptSelfOptimal(intArrayOf(1, 2, 3, 4)).toList().toString())
    }
}