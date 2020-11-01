package problem.facebook.fb_1

import org.junit.Assert
import org.junit.Test

/**
 * Created by randheercode
 * Date: 1/11/20
 * Time: 9:16 pm
 */
class MinimumRemoveToMakeValidParenthesesTest {

    private val obj = MinimumRemoveToMakeValidParentheses()

    @Test
    fun minRemoveToMakeValid() {
        Assert.assertEquals("ab(c)d", obj.minRemoveToMakeValid("a)b(c)d"))
        Assert.assertEquals("lee(t(c)o)de", obj.minRemoveToMakeValid("lee(t(c)o)de)"))
        Assert.assertEquals("", obj.minRemoveToMakeValid("))(("))
    }
}