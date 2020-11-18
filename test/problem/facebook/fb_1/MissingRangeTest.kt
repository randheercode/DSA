package problem.facebook.fb_1

import org.junit.Assert
import org.junit.Test

/**
 * Created by randheercode
 * Date: 18/11/20
 * Time: 7:48 am
 */
class MissingRangeTest {

    private val obj = MissingRange()

    @Test
    fun findMissingRanges() {
        val result = obj.findMissingRanges(intArrayOf(0, 1, 3, 50, 75), 0, 99)
        Assert.assertNotNull(result)
    }
}