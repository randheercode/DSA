package problem.facebook.fb_1

import org.junit.Assert
import org.junit.Test


/**
 * Created by randheercode
 * Date: 2/11/20
 * Time: 8:09 pm
 */
class AddStringsTest {
    private val obj = AddStrings()

    @Test
    fun addStrings() {
        Assert.assertEquals("1010", obj.addStrings("101", "909"))
    }
}