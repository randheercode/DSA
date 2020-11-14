package problem.facebook.fb_1

import org.junit.Assert
import org.junit.Test

/**
 * Created by randheercode
 * Date: 14/11/20
 * Time: 12:01 pm
 */
class BTFromStrTest {
    private val obj = BTFromStr()

    @Test
    fun str2tree() {
        val tree = obj.str2tree("4(2(3)(1))(6(5))")
        Assert.assertNotNull(tree)
    }
}