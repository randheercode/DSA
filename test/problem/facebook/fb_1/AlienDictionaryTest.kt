package problem.facebook.fb_1

import org.junit.Assert
import org.junit.Test

/**
 * Created by randheercode
 * Date: 3/11/20
 * Time: 11:40 am
 */
class AlienDictionaryTest {
    private val obj = AlienDictionary()

    @Test
    fun alienOrder() {
        Assert.assertEquals("wertf", obj.alienOrder(arrayOf("wrt", "wrf", "er", "ett", "rftt")))
        Assert.assertEquals("zx", obj.alienOrder(arrayOf("z", "x")))
        Assert.assertEquals("", obj.alienOrder(arrayOf("z", "x", "z")))
    }
}