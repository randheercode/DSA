package problem.facebook.fb_1

import org.junit.Assert
import org.junit.Test

/**
 * Created by randheercode
 * Date: 3/11/20
 * Time: 11:40 am
 */
class AlienDictionaryTest {
    @Test
    fun alienOrder() {
        Assert.assertEquals("wertf", arrayOf("wrt", "wrf", "er", "ett", "rftt"))
        Assert.assertEquals("zx", arrayOf("z", "x"))
        Assert.assertEquals("", arrayOf("z", "x", "z"))
    }
}