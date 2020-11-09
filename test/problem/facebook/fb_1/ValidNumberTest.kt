package problem.facebook.fb_1

import org.junit.Assert
import org.junit.Test

/**
 * Created by randheercode
 * Date: 9/11/20
 * Time: 10:09 pm
 */
class ValidNumberTest {
    private val obj = ValidNumber()

    @Test
    fun isNumber() {
        Assert.assertTrue(obj.isNumber("0"))
        Assert.assertTrue(obj.isNumber("0.1"))
        Assert.assertFalse(obj.isNumber("abc"))
        Assert.assertFalse(obj.isNumber("1 a"))
        Assert.assertTrue(obj.isNumber("2e10"))
        Assert.assertTrue(obj.isNumber(" -90e3   "))
        Assert.assertFalse(obj.isNumber("1e"))
        Assert.assertFalse(obj.isNumber("e3"))
        Assert.assertTrue(obj.isNumber(" 6e-1"))
        Assert.assertFalse(obj.isNumber(" 99e2.5 "))
        Assert.assertTrue(obj.isNumber("53.5e93"))
        Assert.assertFalse(obj.isNumber(" --6 "))
        Assert.assertFalse(obj.isNumber("-+3"))
        Assert.assertFalse(obj.isNumber("95a54e53"))
    }
}