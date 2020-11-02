package problem.facebook.fb_1

import org.junit.Assert
import org.junit.Test


/**
 * Created by randheercode
 * Date: 2/11/20
 * Time: 8:09 pm
 */
class AddBinaryTest {
    private val obj = AddBinary()

    @Test
    fun addStrings() {
        Assert.assertEquals("100", obj.addBinary("11", "1"))
        Assert.assertEquals("10101", obj.addBinary("1010", "1011"))
    }
}